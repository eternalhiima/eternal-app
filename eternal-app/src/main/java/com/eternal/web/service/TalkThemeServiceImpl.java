/**
 *
 */
package com.eternal.web.service;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.util.Arrays;
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
 * TalkThemeServiceImpl
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class TalkThemeServiceImpl implements TalkThemeService {

    /** トークテーマリポジトリ */
    private final TalkThemeRepository talkThemeRepository;

    /** ユーザーリポジトリ */
    private final UserRepository userRepository;

    /** カテゴリリポジトリ */
    private final CategoryRepository categoryRepository;

    /** メッセージソース */
    private final MessageSourceImpl messageSource;

    /** トークテーマコンバーター */
    private final TalkThemeConverter talkThemeConverter;

    /**
     * @param TalkThemeListRequest request
     * @return TalkThemeListResponse
     */
    @AppLog
    @Override
    public TalkThemeListResponse getTalkThemeListResponse(TalkThemeListRequest request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EternalException(messageSource.getMessage(MessageCode.UNKNOWN_CATEGORY)));
        // TODO: Pagiableを実装して無限スクロールができるように
        List<TalkThemeEntity> talkThemeEntityList = talkThemeRepository.findAll(RepositoryUtil.sorter(request.getSortKey(), request.getSort())).stream()
                .filter(entity -> entity.getCategoryList().contains(category))
                .collect(Collectors.toList());
        // converterでレスポンスDtoに変換し返却
        return talkThemeConverter.convert(talkThemeEntityList);
    }

    /**
     * トークテーマをDBに保存し、レスポンスを返却する
     *
     * @param PostTalkRequest request
     * @return PostTalkThemeResponse
     */
    @AppLog
    @Transactional
    @Override
    public PostTalkResponse postTalk(PostTalkRequest request) {
        // ユーザー名がすでに使用されている場合はエラー
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> throwUserDuplicateException(user.getUserName()));
        // ユーザーを新規作成
        UserEntity user = userRepository.save(UserEntity.of(request.getUserName()));
        // トークテーマを保存
        TalkThemeEntity savedEntity= talkThemeRepository.saveAndFlush(TalkThemeEntity.of(request, user));
        return talkThemeConverter.convert(savedEntity);
    }

    private void throwUserDuplicateException(String userName) {
        throw new EternalException(messageSource.getMessage(MessageCode.POST_DUPLICATE_USER, Arrays.asObjectArray(userName)));
    }
}
