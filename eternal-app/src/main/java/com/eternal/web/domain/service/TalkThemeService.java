package com.eternal.web.domain.service;

import com.eternal.web.dto.request.TalkThemeDetailRequest;
import com.eternal.web.dto.response.TalkThemeDetailResponse;

public interface TalkThemeService {

	TalkThemeDetailResponse getTalkThemeDetail(TalkThemeDetailRequest request);

}
