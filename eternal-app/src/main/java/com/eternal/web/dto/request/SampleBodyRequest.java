package com.eternal.web.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.eternal.web.type.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SampleBodyRequest implements Serializable {

    private final BigDecimal id;

    private final SortType sortType;

    private final List<SampleModel> sampleList;
}
