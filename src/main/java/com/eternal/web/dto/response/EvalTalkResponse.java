/**
 *
 */
package com.eternal.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * {@link EvalTalkResponse}
 *
 * @author taiki0304
 */
@Builder
@Getter
public class EvalTalkResponse {

    /** トークテーマID */
    private Long talkThemeId;

    /** GOOD数 */
    private BigDecimal goodCount;

    /** BAD数 */
    private BigDecimal badCount;

}
