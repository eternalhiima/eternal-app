package com.eternal.web.converter;

import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.response.CategoryListResponse;
import com.eternal.web.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    /**
     * Ref002_カテゴリ一覧取得
     *
     * @param categoryEntityList
     * @return {@link CategoryListResponse}
     */
    public CategoryListResponse convert(List<Category> categoryEntityList) {
        CategoryListResponse response = new CategoryListResponse();
        response.setCategoryList(categoryEntityList.stream()
                .map(e -> new CategoryDto(e.getId(), e.getCategoryName()))
                .collect(Collectors.toList()));
        return response;
    }
}
