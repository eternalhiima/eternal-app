package com.eternal.web.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * {@link EvaluationType}
 *
 * @author taiki0304
 */
@AllArgsConstructor
@Getter
public enum EvaluationType {

    /** GOOD */
    GOOD("01"),
    /** BAD */
    BAD("02");

    /** 評価タイプ値 */
    private String val;

    /**
     * @param val
     * @return Optional<EvaluationType>
     */
    public static Optional<EvaluationType> get(String val) {
        return Stream.of(values())
                .filter(x -> val.equals(x.getVal()))
                .findFirst();
    }
}
