/**
 *
 */
package com.eternal.web.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.TalkThemeEntity;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.TalkThemeRepository;
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

    /** メッセージソース */
    private final MessageSourceImpl messageSource;

    /**
     * @param TalkThemeListRequest request
     * @return TalkThemeListResponse
     */
    @AppLog
    @Override
    public TalkThemeListResponse getTalkThemeResponse(TalkThemeListRequest request) {
        // DBから指定条件でトークテーマを取得
        // converterでレスポンスDtoに変換し返却
        return null;
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
        TalkThemeEntity savedEntity= talkThemeRepository.saveAndFlush(TalkThemeEntity.of(request));
        return createPostTalkResponse(savedEntity);
    }

    private PostTalkResponse createPostTalkResponse(TalkThemeEntity savedEntity) {
        if (Objects.nonNull(savedEntity)) {
            return PostTalkResponse.builder()
                    .isSuccess(Boolean.TRUE)
                    .message(messageSource.getMessage(MessageCode.postTalkSuccess))
                    .talkThemeId(savedEntity.getId())
                    .build();
        } else {
            return PostTalkResponse.builder()
                    .isSuccess(Boolean.FALSE)
                    .message(messageSource.getMessage(MessageCode.postTalkFailure))
                    .build();
        }
    }

}
