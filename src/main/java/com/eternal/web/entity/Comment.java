package com.eternal.web.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    /** コメント */
    private String comment;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /**
     * {@link Comment}を作成する
     *
     * @param talkThemeId
     * @param {@link User} user
     * @param comment
     * @return {@link Comment}
     */
    public static Comment of(Long talkThemeId, User user, String comment) {
        Comment entity = new Comment();
        entity.talkThemeId = talkThemeId;
        entity.user = user;
        entity.comment = comment;
        entity.createDatetime = LocalDateTime.now();
        return entity;
    }
}
