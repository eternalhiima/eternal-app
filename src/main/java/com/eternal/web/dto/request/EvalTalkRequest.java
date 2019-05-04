package com.eternal.web.dto.request;

import javax.validation.constraints.NotNull;
import com.eternal.web.type.EvaluationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
