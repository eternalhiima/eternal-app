package com.eternal.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eternal.web.aop.ApiLog;
import com.eternal.web.config.WebApiEndPoint;
import com.eternal.web.dto.request.CategoryListRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.CategoryListResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.service.CategoryService;
import com.eternal.web.type.ApiComponentType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebApiEndPoint.ROOT_END_POINT)
public class CategoryController {

    /** {@link CategoryService} */
    private final CategoryService categoryService;

    /**
     * Ref002_カテゴリ一覧取得
     *
     * @param {@link TalkThemeListRequest}
     * @return {@link TalkThemeListResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.REF002)
    @RequestMapping(value = WebApiEndPoint.CATEGORYLIST_END_POINT, method = RequestMethod.GET)
    public CategoryListResponse getCategoryList(@Validated CategoryListRequest request) {
        return categoryService.getCategoryList(request);
    }
}
