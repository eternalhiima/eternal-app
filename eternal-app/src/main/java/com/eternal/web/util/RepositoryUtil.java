/**
 *
 */
package com.eternal.web.util;

import org.springframework.data.domain.Sort;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;

/**
 *
 * @author taiki0304
 */
public class RepositoryUtil {

    /**
     * JPA用のソートキーを作成する
     *
     * @param sortKey ソートキー
     * @param sortType ソート順
     * @return Optional<Sort>
     */
    public static Sort sorter(SortKeyType sortKey, SortType sortType) {
        return new Sort(sortType.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, sortKey.getColumn());
    }
}
