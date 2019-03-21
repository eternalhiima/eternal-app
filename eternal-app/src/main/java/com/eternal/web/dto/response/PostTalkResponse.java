/**
 *
 */
package com.eternal.web.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author taiki0304
 *
 */
@Builder
@Getter
public class PostTalkResponse {

    /** 投稿成功フラグ */
    private Boolean isSuccess;

    /** メッセージ */
    private String message;

    /** トークテーマID */
    private Long talkThemeId;
}
