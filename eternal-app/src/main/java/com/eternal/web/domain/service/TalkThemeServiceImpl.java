package com.eternal.web.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.eternal.web.dto.request.TalkThemeDetailRequest;
import com.eternal.web.dto.response.TalkThemeDetailResponse;
@Service
public class TalkThemeServiceImpl implements TalkThemeService {

	@Override
	public TalkThemeDetailResponse getTalkThemeDetail(TalkThemeDetailRequest request) {
		return TalkThemeDetailResponse.builder()
				.id(BigDecimal.ONE)
				.title("トークテーマタイトルテスト")
				.description("hogehoge")
				.build();
	}

}
