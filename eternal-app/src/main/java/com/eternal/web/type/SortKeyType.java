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
    RANK("01", "TODO"),
    /** GOOD数 */
    GOOD("02", "GOOD_COUNT"),
    /** BAD数 */
    BAD("03", "BAD_COUNT"),
    /** トーク数 */
    TALK("04", "TALK_COUNT"),
    /** タイトル名称 */
    TITLE("05", "TITLE");

    /** ソートタイプキー */
    private String key;

    /** ソートカラム */
    private String column;

    /**
     * @param key ソートタイプキー
     * @return Optional<SortKeyType>
     */
    public static Optional<SortKeyType> get(String key) {
        return Arrays.stream(values())
                .filter(x -> key.equals(x.getKey()))
                .findFirst();
    }
}
