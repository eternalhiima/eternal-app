package com.eternal.web.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CommentDto {

    /** コメントID */
    private Long commentId;

    /** コメント */
    private String comment;

    /** ユーザーID */
    private Long userId;

    /** ユーザー名 */
    private String userName;

    /** ユーザサムネイルURL */
    private String userThumbnailPath;

    /** 投稿日時 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postDateTime;
}
