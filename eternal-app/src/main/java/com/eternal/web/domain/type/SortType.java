package com.eternal.web.domain.type;

import java.util.Arrays;
import java.util.stream.Collectors;
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
        return Arrays.stream(values())
                .filter(x -> key.equals(x.getKey()))
                .collect(Collectors.toList()).get(0);
    }
}
