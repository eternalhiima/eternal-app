package com.eternal.web.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CategoryDto {

    /** カテゴリID */
    private Long categoryId;

    /** カテゴリ名 */
    @NotEmpty(message = "{API90009}")
    private String categoryName;
}