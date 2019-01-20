package com.eternal.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TalkThemeDto {

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
    private List<CategoryDto> categoryList;

    /** 投稿者名 */
    private String postUser;

    /** 投稿日時 */
    private LocalDateTime postDateTime;
}
