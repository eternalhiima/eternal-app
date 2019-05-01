package com.eternal.web.dto.request;

import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * {@link CategoryListRequest}
 *
 * @author taiki0304
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CategoryListRequest {

    /** ページ */
    @NotNull(message = "{API90009}")
    private int page;

    /** 取得数 */
    @NotNull(message = "{API90009}")
    @Max(message = "{API90003}", value = 100)
    private int size;

    /** キーワード */
    private String keyword;

    /** ソート順 */
    private SortType sort;
}
