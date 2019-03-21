/**
 *
 */
package com.eternal.web.dto.request;

import java.util.List;
import com.eternal.web.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PostTalkThemeRequest
 *
 * @author taiki0304
 */
@AllArgsConstructor
@Getter
public class PostTalkRequest {

    /** トークテーマタイトル */
    private String title;

    /** カテゴリリスト */
    private List<CategoryDto> categoryList;

    /** トークテーマ内容 */
    private String content;

    /** 投稿者名 */
    private String userName;

    /** 関連URL */
    private String relatedUrl;
}
