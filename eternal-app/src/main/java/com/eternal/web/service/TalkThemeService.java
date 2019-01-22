package com.eternal.web.service;

import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;

/**
 * TalkThemeService
 *
 * @author taiki0304
 */
public interface TalkThemeService {

    public TalkThemeListResponse getTalkThemeResponse(TalkThemeListRequest request);

    public PostTalkResponse postTalk(PostTalkRequest request);
}
