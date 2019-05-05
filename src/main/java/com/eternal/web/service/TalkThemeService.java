package com.eternal.web.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.TalkThemeConverter;
import com.eternal.web.dto.request.EvalTalkRequest;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.EvalTalkResponse;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.Category;
import com.eternal.web.entity.TalkTheme;
import com.eternal.web.entity.User;
import com.eternal.web.exception.ServiceException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.repository.TalkThemeRepository;
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

    /** {@link UserService} */
    private final UserService userService;

    /** {@liml CategoryService} */
    private final CategoryService categoryService;

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
        List<TalkTheme> talkThemeEntityList = talkThemeRepository.findAll(PageRequest.of(request.getPage(),
                request.getSize(), RepositoryUtil.sorter(request.getSortKey(), request.getSort()))).getContent();
        if (Objects.nonNull(request.getCategoryId())) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_CATEGORY,
                            messageSource.getMessage(MessageCode.UNKNOWN_CATEGORY)));
            // カテゴリでフィルタリング
            // FIXME: カテゴリでのフィルタリング機能がうまくいってないかも
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
        // ユーザーを新規作成
        User user = userService.addUser(request.getUserName());
        // カテゴリが新規の場合は保存、すでに存在する場合は使用回数を増やす
        List<Category> categoryList = categoryService.addOrUpdateCategory(request.getCategoryList());
        // トークテーマを保存
        TalkTheme talkTheme =
                talkThemeRepository.saveAndFlush(TalkTheme.of(request, user.getId(), categoryList));
        String message = Objects.nonNull(talkTheme)
                ? messageSource.getMessage(MessageCode.POST_SUCCESS, Arrays.asList(talkTheme.getTitle()))
                : messageSource.getMessage(MessageCode.POST_FAILURE);
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
        TalkTheme talkTheme = talkThemeRepository.findById(request.getTalkThemeId())
                .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_TALK,
                        messageSource.getMessage(MessageCode.UNKNOWN_TALK)));
        // トークテーマの評価を更新し保存
        TalkTheme updatedEntity = talkThemeRepository.saveAndFlush(talkTheme.eval(request.getEvaluate()));
        return talkThemeConverter.convertEval(updatedEntity);
    }
}
