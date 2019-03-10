package com.eternal.web.entity;

import java.math.BigDecimal;
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
public class CommentEntity extends AbstractEntity {

    /** コメント */
    private String comment;

    /** ユーザーID */
    private BigDecimal userId;

    /** トークテーマID */
    private BigDecimal talkThemeId;

    /** 作成日時 */
    private LocalDateTime createDatetime;

}
