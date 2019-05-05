package com.eternal.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @Size(max = 12, message="{API90013}")
    private String categoryName;
}
