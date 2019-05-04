package com.eternal.web.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * {@link PostTalkResponse}
 *
 * @author taiki0304
 */
@Builder
@Getter
public class PostTalkResponse {

    /** メッセージ */
    private String message;

    /** トークテーマID */
    private Long talkThemeId;
}
