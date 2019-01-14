package com.eternal.web.service;

import com.eternal.web.dto.request.SampleBodyRequest;
import com.eternal.web.dto.request.SampleQueryRequest;
import com.eternal.web.dto.response.SampleResponse;

public interface SampleService {

    public SampleResponse getQuerySample(SampleQueryRequest request);

    public SampleResponse getBodySample(SampleBodyRequest request);

}
