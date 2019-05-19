package com.eternal.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eternal.web.aop.ApiLog;
import com.eternal.web.config.WebApiEndPoint;
import com.eternal.web.dto.request.SearchResultListRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.SearchResultListResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.service.SearchService;
import com.eternal.web.type.ApiComponentType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebApiEndPoint.ROOT_END_POINT)
public class SearchController {

    /** {@link SearchService} */
    private final SearchService searchService;

    /**
     * Ref004_Ref004_トークテーマ検索結果一覧取得
     *
     * @param {@link TalkThemeListRequest}
     * @return {@link TalkThemeListResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.REF004)
    @RequestMapping(value = WebApiEndPoint.SEARCH_END_POINT, method = RequestMethod.GET)
    public SearchResultListResponse getCategoryList(@Validated SearchResultListRequest request) {
        return searchService.getResult(request);
    }
}
