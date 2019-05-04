package com.eternal.web.type;

import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
    // TODO: 総合ランキング用のカラムをTalkThemeEntityに追加する
    RANK("01", "TODO"),
    /** GOOD数 */
    GOOD("02", "goodCount"),
    /** BAD数 */
    BAD("03", "badCount"),
    /** トーク数 */
    TALK("04", "talkCount"),
    /** タイトル名称 */
    TITLE("05", "title"),
    /** 使用回数 */
    USED("06", "usedCount");

    /** ソートタイプキー */
    private String key;

    /** ソートカラム */
    private String column;

    /**
     * @param key ソートタイプキー
     * @return Optional<SortKeyType>
     */
    public static Optional<SortKeyType> get(String key) {
        return Stream.of(values())
                .filter(x -> key.equals(x.getKey()))
                .findFirst();
    }
}
