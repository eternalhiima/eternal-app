package com.eternal.web.dto.response;

import com.eternal.web.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;

/**
 * {@link PostCommentResponse}
 *
 * @author taiki0304
 */
@Builder
@Getter
public class PostCommentResponse {

    /** メッセージ */
    private String message;

    /** トークテーマID */
    private Long talkThemeId;

    /** {@link CommentDto} */
    private CommentDto comment;
}
