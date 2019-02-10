package com.eternal.web.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryDto {

    /** カテゴリID */
    private BigDecimal categoryId;

    /** カテゴ名 */
    private String categoryName;
}
