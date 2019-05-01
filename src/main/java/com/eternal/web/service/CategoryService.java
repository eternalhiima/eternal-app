/**
 *
 */
package com.eternal.web.service;

import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.CategoryConverter;
import com.eternal.web.converter.TalkThemeConverter;
import com.eternal.web.dto.request.CategoryListRequest;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.CategoryListResponse;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.CategoryEntity;
import com.eternal.web.entity.TalkThemeEntity;
import com.eternal.web.entity.UserEntity;
import com.eternal.web.exception.ServiceException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.repository.TalkThemeRepository;
import com.eternal.web.repository.UserRepository;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
