package com.eternal.web.service;

import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.TalkThemeListResponse;

/**
 * TalkThemeService
 *
 * @author taiki0304
 */
public interface TalkThemeService {

    public TalkThemeListResponse getTalkThemeResponse(TalkThemeListRequest request);
}
