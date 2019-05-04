package com.eternal.web.dto.response;

import java.util.List;
import com.eternal.web.dto.CategoryDto;
import lombok.Data;

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
