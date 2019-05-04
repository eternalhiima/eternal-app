package com.eternal.web.controller;

import com.eternal.web.dto.request.EvalTalkRequest;
import com.eternal.web.dto.response.EvalTalkResponse;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eternal.web.aop.ApiLog;
import com.eternal.web.config.WebApiEndPoint;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.service.TalkThemeService;
import com.eternal.web.type.ApiComponentType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebApiEndPoint.ROOT_END_POINT)
public class TalkThemeController {

    /** {@link TalkThemeService} */
    private final TalkThemeService talkThemeService;

    /**
     * Ref001_トークテーマ一覧取得
     *
     * @param {@link TalkThemeListRequest}
     * @return {@link TalkThemeListResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.REF001)
    @RequestMapping(value = WebApiEndPoint.TALKLIST_END_POINT, method = RequestMethod.GET)
    public TalkThemeListResponse getTalkThemeList(@Validated TalkThemeListRequest request) {
        return talkThemeService.getTalkThemeList(request);
    }

    /**
     * Upd001_トークテーマ投稿
     *
     * @param {@link PostTalkRequest} リクエスト
     * @return {@link PostTalkResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.UPD001)
    @RequestMapping(value = WebApiEndPoint.POSTTALK_END_POINT, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostTalkResponse postTalkTheme(@RequestBody @Validated PostTalkRequest request) {
        return talkThemeService.postTalk(request);
    }

    /**
     * Upd002_トークテーマ評価
     *
     * @param {@link EvalTalkRequest} リクエスト
     * @return {@link EvalTalkResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.UPD002)
    @RequestMapping(value = WebApiEndPoint.EVALUATE_END_POINT, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public EvalTalkResponse evalTalkTheme(@RequestBody @Validated EvalTalkRequest request) {
        return talkThemeService.evalTalk(request);
    }
}
