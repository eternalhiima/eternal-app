package com.eternal.web.dto.response;

import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.TalkThemeDto;
import lombok.Data;

import java.util.List;

/**
 * {@link CategoryListResponse}
 *
 * @author taiki0304
 */
@Data
public class CategoryListResponse {

    /** カテゴリリスト */
    private List<CategoryDto> categoryList;
}
