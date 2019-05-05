package com.eternal.web.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.CategoryConverter;
import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.request.CategoryListRequest;
import com.eternal.web.dto.response.CategoryListResponse;
import com.eternal.web.entity.Category;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;

/**
 * {@link CategoryService}
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

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
        Sort sort = Objects.nonNull(request.getSort()) ? RepositoryUtil.sorter(SortKeyType.USED, request.getSort())
                : Sort.unsorted();
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);
        // リクエストパラメータにキーワードが指定されている場合はキーワードで検索結果を絞る
        List<Category> categoryEntityList = Objects.nonNull(request.getKeyword())
                ? categoryRepository.findByCategoryNameContaining(request.getKeyword(), pageRequest).getContent()
                : categoryRepository.findAll(pageRequest).getContent();
        return converter.convert(categoryEntityList);
    }

    /**
     * カテゴリが新規の場合は保存、すでに存在する場合は使用回数を増やす]
     *
     * @param {@link {@link CategoryDto} categoryDtoList
     * @return {@link Category}
     */
    @AppLog
    public List<Category> addOrUpdateCategory(List<CategoryDto> categoryDtoList) {
        List<Category> categoryList = categoryDtoList.stream().map(d -> {
            if (Objects.nonNull(d.getCategoryId())) {
                return categoryRepository.findById(d.getCategoryId()).get().add();
            }
            return Category.of(d);
        }).collect(Collectors.toList());
        return categoryRepository.saveAll(categoryList);
    }
}
