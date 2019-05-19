package com.eternal.web.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * {@link SearchResultListRequest}
 *
 * @author taiki0304
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class SearchResultListRequest {

    /** ページ */
    @NotNull(message = "{API90009}")
    @Min(message = "{API90004}", value = 0)
    private int page;

    /** 取得数 */
    @NotNull(message = "{API90009}")
    @Min(message = "{API90004}", value = 0)
    @Max(message = "{API90003}", value = 100)
    private int size;

    /** 検索キーワード  */
    @NotEmpty(message = "{API90009}")
    private String keyword;

    /** カテゴリID */
    private Long categoryId;

    /** ソートキー */
    private SortKeyType sortKey;

    /** ソート順 */
    private SortType sort;
}
