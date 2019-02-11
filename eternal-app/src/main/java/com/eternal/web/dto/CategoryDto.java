package com.eternal.web.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {

    /** カテゴリID */
    private BigDecimal categoryId;

    /** カテゴ名 */
    private String categoryName;
}
