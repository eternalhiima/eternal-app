package com.eternal.web.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {

    /** カテゴリID */
    private Long categoryId;

    /** カテゴ名 */
    private String categoryName;
}
