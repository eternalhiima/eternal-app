package com.eternal.web.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMMENT")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends AbstractEntity {

    /** トークテーマID */
    private Long talkThemeId;

    /** ユーザーID */
    private Long userId;

    /** コメント */
    private String comment;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /**
     * {@link Comment}を作成する
     *
     * @param talkThemeId
     * @param userId
     * @param comment
     * @return {@link Comment}
     */
    public static Comment of(Long talkThemeId, Long userId, String comment) {
        Comment entity = new Comment();
        entity.talkThemeId = talkThemeId;
        entity.userId = userId;
        entity.comment = comment;
        entity.createDatetime = LocalDateTime.now();
        return entity;
    }
}
