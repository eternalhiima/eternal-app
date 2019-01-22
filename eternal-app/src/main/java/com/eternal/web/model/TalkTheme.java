/**
 *
 */
package com.eternal.web.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author taiki0304
 *
 */
@Data
public class TalkTheme {

    /** トークテーマID */
    private BigDecimal talkThemeId;

    /** ランキング順 */
    private BigDecimal order;

    /** トークテーマタイトル */
    private String title;

    /** トークテーマ概要 */
    private String description;

    /** サムネイルURL */
    private String thumbnailUrl;

    /** GOOD数 */
    private BigDecimal goodCount;

    /** BAD数 */
    private BigDecimal badCount;

    /** トーク数 */
    private BigDecimal talkedCount;

    /** カテゴリリスト */
    private List<Category> categoryList;

    /** 投稿者名 */
    private User postUser;

    /** 投稿日時 */
    private LocalDateTime postDateTime;

}
