/**
 *
 */
package com.eternal.web.service;

import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.CategoryConverter;
import com.eternal.web.dto.request.CategoryListRequest;
import com.eternal.web.dto.response.CategoryListResponse;
import com.eternal.web.entity.CategoryEntity;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;

/**
 * {@link CategoryService}
 *
 * @author taiki0304
 */
@Service @RequiredArgsConstructor public class CategoryService {

    /** {@link CategoryRepository} */
    private final CategoryRepository categoryRepository;

    /** {@link CategoryConverter} */
    private final CategoryConverter converter;

    /**
     * カテゴリのリストを取得する
     *
     * @param {@link CategoryListRequest} request
     * @return {@link CategoryListResponse}
     */
    @AppLog
    public CategoryListResponse getCategoryList(CategoryListRequest request) {
        // リクエストパラメータにソート条件がある場合は、使用回数でソートする
        Sort sort = Objects.nonNull(request.getSort()) ?
                RepositoryUtil.sorter(SortKeyType.USED, request.getSort()) :
                Sort.unsorted();
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);
        // リクエストパラメータにキーワードが指定されている場合はキーワードで検索結果を絞る
        List<CategoryEntity> categoryEntityList = Objects.nonNull(request.getKeyword()) ?
                categoryRepository.findByCategoryNameContaining(request.getKeyword(), pageRequest).getContent() :
                categoryRepository.findAll(pageRequest).getContent();
        return converter.convert(categoryEntityList);
    }
}
