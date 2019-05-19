package com.eternal.web.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.eternal.web.converter.SearchConverter;
import com.eternal.web.dto.request.SearchResultListRequest;
import com.eternal.web.dto.response.SearchResultListResponse;
import com.eternal.web.entity.Category;
import com.eternal.web.entity.TalkTheme;
import com.eternal.web.repository.CategoryRepository;
import com.eternal.web.repository.TalkThemeRepository;
import com.eternal.web.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;

/**
 * {@link SearchService}
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    /** {@link TalkThemeRepository} */
    private final TalkThemeRepository talkThemeRepository;

    private final SearchConverter searchConverter;

    /** {@link CategoryRepository} */
    private final CategoryService categoryService;

    public SearchResultListResponse getResult(SearchResultListRequest request) {
        // ソートキーを指定した場合はソート条件を指定
        Sort sort = Objects.nonNull(request.getSortKey()) && Objects.nonNull(request.getSort())
                ? RepositoryUtil.sorter(request.getSortKey(), request.getSort())
                : Sort.unsorted();
        List<TalkTheme> talkThemeList =
                talkThemeRepository.findByTitleContainingOrContentContaining(request.getKeyword(), request.getKeyword(),
                        PageRequest.of(request.getPage(), request.getSize(), sort)).getContent();
        if (Objects.nonNull(request.getCategoryId())) {
            // カテゴリを指定した場合はカテゴリでフィルタリング
            Category category = categoryService.findById(request.getCategoryId());
            talkThemeList = talkThemeList.stream().filter(e -> e.getCategoryList().contains(category))
                    .collect(Collectors.toList());
        }
        return searchConverter.convert(talkThemeList);
    }
}
