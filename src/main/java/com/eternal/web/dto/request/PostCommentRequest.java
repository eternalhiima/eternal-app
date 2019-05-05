package com.eternal.web.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class PostCommentRequest {

    /** トークテーマID　*/
    @NotNull(message = "{API90009}")
    private Long talkThemeId;

    /** コメント */
    @NotEmpty(message = "{API90009}")
    @Size(max = 50, message="{API90013}")
    private String comment;

    /** ユーザーID */
    private Long userId;
}
