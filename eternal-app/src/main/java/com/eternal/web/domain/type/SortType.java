package com.eternal.web.domain.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * SortType
 * @author taiki0304
 *
 */
@AllArgsConstructor
@Getter
public enum SortType {

    /** 昇順 */
    ASC("asc"),
    /** 降順 */
    DESC("desc");

    /** ソートキー */
    private String key;

    /**
     *
     * @param key キー
     * @return SortType ソートタイプ
     */
    public static SortType get(String key) {
        List<SortType> filteredList = Arrays.stream(values())
                .filter(x -> key.equals(x.getKey()))
                .collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(filteredList) ? filteredList.get(0) : null;
    }
}
