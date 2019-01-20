package com.eternal.web.type;

import java.util.Arrays;
import java.util.Optional;
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
     * @param key キー
     * @return Optional<SortType> ソートタイプ
     */
    public static Optional<SortType> get(String key) {
        return Arrays.stream(values())
                .filter(x -> key.equals(x.getKey()))
                .findFirst();
    }
}
