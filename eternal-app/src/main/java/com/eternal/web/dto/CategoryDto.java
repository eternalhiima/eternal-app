package com.eternal.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CategoryDto {

    /** カテゴリID */
    private Long categoryId;

    /** カテゴ名 */
    private String categoryName;
}
