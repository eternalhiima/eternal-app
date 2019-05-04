/**
 *
 */
package com.eternal.web.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.eternal.web.dto.request.EvalTalkRequest;
import com.eternal.web.dto.response.EvalTalkResponse;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.TalkThemeConverter;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.CategoryEntity;
import com.eternal.web.entity.TalkThemeEntity;
import com.eternal.web.entity.UserEntity;
import com.eternal.web.exception.ServiceException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.repository.TalkThemeRepository;
import com.eternal.web.repository.UserRepository;
import com.eternal.web.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;

/**
 * {@link TalkThemeService}
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class TalkThemeService {

    /** {@link TalkThemeRepository} */
    private final TalkThemeRepository talkThemeRepository;

    /** {@link UserRepository} */
    private final UserRepository userRepository;

    /** {@link CategoryRepository} */
    private final CategoryRepository categoryRepository;

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /** {@link TalkThemeConverter} */
    private final TalkThemeConverter talkThemeConverter;

    /**
     * トークテーマのリストを取得する
     *
     * @param {@link TalkThemeListRequest} request
     * @return {@link TalkThemeListResponse}
     */
    @AppLog
    public TalkThemeListResponse getTalkThemeList(TalkThemeListRequest request) {
        // DBよりトークテーマのリストを取得
        List<TalkThemeEntity> talkThemeEntityList = talkThemeRepository.findAll(
                PageRequest.of(request.getPage(), request.getSize(),
                        RepositoryUtil.sorter(request.getSortKey(), request.getSort()))).getContent();
        if (Objects.nonNull(request.getCategoryId())) {
            CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_CATEGORY,
                            messageSource.getMessage(MessageCode.UNKNOWN_CATEGORY)));
            // カテゴリでフィルタリング
            talkThemeEntityList = talkThemeEntityList.stream().filter(e -> e.getCategoryList().contains(category))
                    .collect(Collectors.toList());
        }
        return talkThemeConverter.convert(talkThemeEntityList);
    }

    /**
     * トークテーマをDBに保存し、レスポンスを返却する
     *
     * @param {@link PostTalkRequest} request
     * @return {@link PostTalkResponse}
     */
    @AppLog
    @Transactional
    public PostTalkResponse postTalk(PostTalkRequest request) {
        // ユーザー名がすでに使用されている場合はエラー
        userRepository.findByUserName(request.getUserName())
                .ifPresent(u -> throwUserDuplicateException(u.getUserName()));
        // ユーザーを新規作成
        UserEntity user = userRepository.saveAndFlush(UserEntity.of(request.getUserName()));
        // カテゴリが新規の場合は保存、すでに存在する場合は使用回数を増やす
        List<CategoryEntity> categoryList = categoryRepository.saveAll(request.getCategoryList().stream().map(d -> {
            if (Objects.nonNull(d.getCategoryId())) {
                return categoryRepository.findById(d.getCategoryId()).get().add();
            }
            return CategoryEntity.of(d);
        }).collect(Collectors.toList()));
        categoryRepository.flush();
        // トークテーマを保存
        TalkThemeEntity talkTheme =
                talkThemeRepository.saveAndFlush(TalkThemeEntity.of(request, user.getId(), categoryList));
        String message = Objects.nonNull(talkTheme) ?
                messageSource.getMessage(MessageCode.POST_TALK_SUCCESS, Arrays.asList(talkTheme.getTitle())) :
                messageSource.getMessage(MessageCode.POST_TALK_FAILURE);
        return talkThemeConverter.convert(talkTheme, message);
    }

    /**
     * トークテーマの評価を更新する
     *
     * @param {@link EvalTalkRequest} request
     * @return {@link EvalTalkResponse}
     */
    @AppLog
    @Transactional
    public EvalTalkResponse evalTalk(EvalTalkRequest request) {
        // トークテーマをDBより取得
        TalkThemeEntity talkTheme = talkThemeRepository.findById(request.getTalkThemeId())
                .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_TALK,
                        messageSource.getMessage(MessageCode.UNKNOWN_TALK)));
        // トークテーマの評価を更新し保存
        TalkThemeEntity updatedEntity =
                talkThemeRepository.saveAndFlush(talkTheme.eval(talkTheme, request.getEvaluate()));
        return talkThemeConverter.convertEval(updatedEntity);
    }

    private void throwUserDuplicateException(String userName) {
        throw new ServiceException(MessageCode.POST_DUPLICATE_USER,
                messageSource.getMessage(MessageCode.POST_DUPLICATE_USER, Arrays.asList(userName)));
    }
}
