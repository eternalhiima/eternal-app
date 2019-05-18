package com.eternal.web.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * {@link CommentListRequest}
 *
 * @author taiki0304
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CommentListRequest {

    /** トークテーマID */
    @NotNull(message = "{API90009}")
    private Long talkThemeId;

    /** ページ */
    @NotNull(message = "{API90009}")
    @Min(message = "{API90004}", value = 0)
    private int page;

    /** 取得数 */
    @NotNull(message = "{API90009}")
    @Min(message = "{API90004}", value = 0)
    @Max(message = "{API90003}", value = 100)
    private int size;
}
