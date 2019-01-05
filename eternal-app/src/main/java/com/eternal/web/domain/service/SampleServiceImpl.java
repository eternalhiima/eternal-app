package com.eternal.web.domain.service;

import org.springframework.stereotype.Service;
import com.eternal.web.dto.request.SampleBodyRequest;
import com.eternal.web.dto.request.SampleQueryRequest;
import com.eternal.web.dto.response.SampleResponse;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public SampleResponse getQuerySample(SampleQueryRequest request) {
        return SampleResponse.builder()
                .id(request.getId())
                .title("RequestParam")
                .description("key: " + request.getKey() + ", SortType: "  + request.getSortType()).build();
    }

    @Override
    public SampleResponse getBodySample(SampleBodyRequest request) {
        return SampleResponse.builder()
                .id(request.getId())
                .title("RequestBody")
                .description(request.getSampleList().toString())
                .sortType(request.getSortType()).build();
    }
}