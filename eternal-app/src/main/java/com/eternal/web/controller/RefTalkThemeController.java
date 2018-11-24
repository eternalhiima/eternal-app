package com.eternal.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eternal.web.domain.service.TalkThemeService;
import com.eternal.web.dto.request.TalkThemeDetailRequest;
import com.eternal.web.dto.response.TalkThemeDetailResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hiima/api/internal/v1")
public class RefTalkThemeController {

	/** トークテーマサービス */
	private final TalkThemeService talkThemeService;

	/**
	 * Ref002_トークテーマ詳細取得
	 * @param talkThemeDetailResponse
	 * @return TalkThemeDetailResponse
	 */
	@RequestMapping(value="/talkDetail", method=RequestMethod.GET)
	public TalkThemeDetailResponse getTalkThemeDetail(@RequestParam TalkThemeDetailRequest request) {
		return talkThemeService.getTalkThemeDetail(request);
	}
 }
