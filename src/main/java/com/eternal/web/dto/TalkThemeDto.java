package com.eternal.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TalkThemeDto {

    /** トークテーマID */
    private Long talkThemeId;

    /** ランキング順 */
    private BigDecimal order;

    /** トークテーマタイトル */
    private String title;

    /** トークテーマ内容 */
    private String content;

    /** サムネイルパス */
    private String thumbnailPath;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postDateTime;
}
