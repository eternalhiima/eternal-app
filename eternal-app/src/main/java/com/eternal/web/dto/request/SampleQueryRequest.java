package com.eternal.web.dto.request;

import java.math.BigDecimal;
import com.eternal.web.domain.type.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SampleQueryRequest {

    private final BigDecimal id;

    private final String key;

    private final SortType sortType;

    public static SampleQueryRequest of(BigDecimal id, String key, String sortType) {
        // 引数の型チェックなどのバリデーションを実装する
        return new SampleQueryRequest(id, key, SortType.get(sortType));
    }
}
