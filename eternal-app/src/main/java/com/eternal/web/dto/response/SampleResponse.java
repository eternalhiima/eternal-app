package com.eternal.web.dto.response;

import java.math.BigDecimal;
import com.eternal.web.type.SortType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SampleResponse {

    private final BigDecimal id;

    private final String title;

    private final String description;

    private final SortType sortType;
}
