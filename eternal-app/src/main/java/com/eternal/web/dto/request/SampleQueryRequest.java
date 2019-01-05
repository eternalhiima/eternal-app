package com.eternal.web.dto.request;

import java.math.BigDecimal;
import java.util.Objects;
import com.eternal.web.domain.type.SortType;
import com.eternal.web.message.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SampleQueryRequest {

    private final BigDecimal id;

    private final String key;

    private final SortType sortType;

    public static SampleQueryRequest of(BigDecimal id, String key, String sortType) {
        SortType type = SortType.get(sortType);
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException(MessageCode.API0001E);
        }
        return new SampleQueryRequest(id, key, type);
    }
}
