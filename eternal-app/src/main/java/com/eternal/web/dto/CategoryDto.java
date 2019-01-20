package com.eternal.web.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto {

    /** カテゴリID */
    private BigDecimal categoryId;

    /** カテゴ名 */
    private String categoryName;
}
