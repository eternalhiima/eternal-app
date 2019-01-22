/**
 *
 */
package com.eternal.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;

/**
 * TalkThemeServiceImpl
 *
 * @author taiki0304
 */
@Service
public class TalkThemeServiceImpl implements TalkThemeService {

    /**
     * @param TalkThemeListRequest request
     * @return TalkThemeListResponse
     */
    @AppLog
    @Override
    public TalkThemeListResponse getTalkThemeResponse(TalkThemeListRequest request) {
        // TODO: 実装
        return null;
    }

    /**
     * @param PostTalkRequest request
     * @return PostTalkThemeResponse
     */
    @AppLog
    @Transactional
    @Override
    public PostTalkResponse postTalk(PostTalkRequest request) {
        // TODO: 実装
        return null;
    }

}
