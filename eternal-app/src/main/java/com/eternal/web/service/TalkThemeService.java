/**
 *
 */
package com.eternal.web.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.assertj.core.util.Arrays;
import org.springframework.context.MessageSource;
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
import com.eternal.web.exception.EternalException;
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
     * @param {@link TalkThemeListRequest} request
     * @return {@link TalkThemeListResponse}
     */
    @AppLog
    public TalkThemeListResponse getTalkThemeListResponse(TalkThemeListRequest request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EternalException(MessageCode.UNKNOWN_CATEGORY,
                        messageSource.getMessage(MessageCode.UNKNOWN_CATEGORY)));
        // TODO: Pagiableを実装して無限スクロールができるように
        List<TalkThemeEntity> talkThemeEntityList =
                talkThemeRepository.findAll(RepositoryUtil.sorter(request.getSortKey(), request.getSort())).stream()
                        .filter(e -> e.getCategoryList().contains(category)).collect(Collectors.toList());
        // converterでレスポンスDtoに変換し返却
        return talkThemeConverter.convert(talkThemeEntityList);
    }

    /**
     * トークテーマをDBに保存し、レスポンスを返却する
     *
     * @param {@link PostTalkRequest} request
     * @return {@link PostTalkThemeResponse}
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
        List<CategoryEntity> categoryList = categoryRepository.saveAll(request.getCategoryList().stream()
                .map(d -> {
                    if (Objects.nonNull(d.getCategoryId())) {
                        return categoryRepository.findById(d.getCategoryId()).get().add();
                    }
                    return CategoryEntity.of(d);
                }).collect(Collectors.toList()));
        categoryRepository.flush();
        // トークテーマを保存
        TalkThemeEntity talkTheme =
                talkThemeRepository.saveAndFlush(TalkThemeEntity.of(request, user.getId(), categoryList));
        return talkThemeConverter.convert(talkTheme);
    }

    private void throwUserDuplicateException(String userName) {
        throw new EternalException(MessageCode.POST_DUPLICATE_USER,
                messageSource.getMessage(MessageCode.POST_DUPLICATE_USER, Arrays.array(userName)));
    }
}
