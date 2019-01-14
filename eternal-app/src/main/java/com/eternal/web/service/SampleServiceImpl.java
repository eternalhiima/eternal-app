package com.eternal.web.service;

import org.springframework.stereotype.Service;
import com.eternal.web.aop.AppLog;
import com.eternal.web.dto.request.SampleBodyRequest;
import com.eternal.web.dto.request.SampleQueryRequest;
import com.eternal.web.dto.response.SampleResponse;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    @AppLog
    public SampleResponse getQuerySample(SampleQueryRequest request) {
        return SampleResponse.builder()
                .id(request.getId())
                .title("RequestParam")
                .description("key: " + request.getKey() + ", SortType: "  + request.getSortType()).build();
    }

    @Override
    @AppLog
    public SampleResponse getBodySample(SampleBodyRequest request) {
        return SampleResponse.builder()
                .id(request.getId())
                .title("RequestBody")
                .description(request.getSampleList().toString())
                .sortType(request.getSortType()).build();
    }
}
