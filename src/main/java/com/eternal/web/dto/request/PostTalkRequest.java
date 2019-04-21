/**
 *
 */
package com.eternal.web.dto.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import com.eternal.web.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link PostTalkRequest}
 *
 * @author taiki0304
 */
@AllArgsConstructor
@Getter
public class PostTalkRequest {

    /** トークテーマタイトル */
    @NotEmpty(message = "{API90001}")
    private String title;

    /** カテゴリリスト */
    @Valid
    private List<CategoryDto> categoryList;

    /** トークテーマ内容 */
    @NotEmpty(message = "{API90001}")
    private String content;

    /** 投稿者名 */
    @NotEmpty(message = "{API90001}")
    private String userName;

    /** 関連URL */
    private String relatedUrl;
}
