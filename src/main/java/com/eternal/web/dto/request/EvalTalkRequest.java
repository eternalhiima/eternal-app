package com.eternal.web.dto.request;

import com.eternal.web.type.EvaluationType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * {@link EvalTalkRequest}
 *
 * @author taiki0304
 */
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class EvalTalkRequest {

    /** トークテーマID */
    @NotNull(message = "{API90009}")
    private Long talkThemeId;

    /** 評価 */
    @NotNull(message = "{API90009}")
    private EvaluationType evaluate;
}
