package com.eternal.web.type;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * SortKeyType
 *
 * @author taiki0304
 */
@AllArgsConstructor
@Getter
public enum SortKeyType {

    /** 総合ランキング */
    RANK("01"),
    /** GOOD数 */
    GOOD("02"),
    /** BAD数 */
    BAD("03"),
    /** トーク数 */
    TALK("04"),
    /** タイトル名称 */
    TITLE("05");

    /** ソートタイプキー */
    private String key;

    /**
     * @param key　ソートタイプキー
     * @return Optional<SortKeyType>
     */
    public static Optional<SortKeyType> get(String key) {
        return Arrays.stream(values())
                .filter(x -> key.equals(x.getKey()))
                .findFirst();
    }
}
