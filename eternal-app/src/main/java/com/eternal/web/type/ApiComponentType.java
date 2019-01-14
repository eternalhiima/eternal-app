package com.eternal.web.type;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ApiComponentType
 *
 * @author taiki0304
 *
 */
@AllArgsConstructor
@Getter
public enum ApiComponentType {

    SAMPLE1(BigDecimal.valueOf(1), "sample1"),
    SAMPLE2(BigDecimal.valueOf(2), "sample2");

    /** APIID */
    private BigDecimal apiId;

    /** API名 */
    private String name;
}
