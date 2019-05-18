/**
 *
 */
package com.eternal.web.dto.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.eternal.web.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * {@link PostTalkRequest}
 *
 * @author taiki0304
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PostTalkRequest {

    /** トークテーマタイトル */
    @NotEmpty(message = "{API90009}")
    @Size(max = 30, message="{API90013}")
    private String title;

    /** カテゴリリスト */
    @Valid
    private List<CategoryDto> categoryList;

    /** トークテーマ内容 */
    @NotEmpty(message = "{API90009}")
    @Size(max = 500, message="{API90013}")
    private String content;

    /** 投稿者名 */
    @NotEmpty(message = "{API90009}")
    @Size(max = 10, message="{API90013}")
    private String userName;

    /** 関連URL */
    private String relatedUrl;
}
