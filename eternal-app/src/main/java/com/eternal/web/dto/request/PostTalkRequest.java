/**
 *
 */
package com.eternal.web.dto.request;

import java.util.List;
import com.eternal.web.dto.CategoryDto;
import lombok.Builder;
import lombok.Getter;

/**
 * PostTalkThemeRequest
 *
 * @author taiki0304
 */
@Builder
@Getter
public class PostTalkRequest {

    /** トークテーマタイトル */
    private String title;

    /** カテゴリリスト */
    private List<CategoryDto> categoryList;

    /** トークテーマ概要 */
    private String description;

    /** 投稿者名 */
    private String userName;

    /** 関連URL */
    private String relatedUrl;
}
