package com.eternal.web.controller;


import java.math.BigDecimal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eternal.web.aop.ApiLog;
import com.eternal.web.config.WebApiEndPoint;
import com.eternal.web.dto.request.SampleBodyRequest;
import com.eternal.web.dto.request.SampleQueryRequest;
import com.eternal.web.dto.response.SampleResponse;
import com.eternal.web.service.SampleService;
import com.eternal.web.type.ApiComponentType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebApiEndPoint.ROOT_END_POINT)
public class SampleController {

    /** サンプルサービス */
    private final SampleService sampleService;

    /**
     * RequestParamのテスト
     *
     * @param id id
     * @param key キー
     * @return SampleResponse
     */
    @ApiLog(apiComponentType = ApiComponentType.SAMPLE1)
    @RequestMapping(value = WebApiEndPoint.QUERY_END_POINT, method = RequestMethod.GET)
    public SampleResponse test1(@RequestParam BigDecimal id, @RequestParam String key, @RequestParam String sortType) {
        return sampleService.getQuerySample(SampleQueryRequest.of(id, key, sortType));
    }

    /**
     * RequestBodyのテスト
     *
     * @param SampleBodyRequest request
     * @return SampleResponse
     */
    @ApiLog(apiComponentType = ApiComponentType.SAMPLE2)
    @RequestMapping(value = WebApiEndPoint.BODY_END_POINT, method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public SampleResponse getSample(@RequestBody SampleBodyRequest request) {
        return sampleService.getBodySample(request);
    }
}
