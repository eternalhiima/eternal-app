package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMMENT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity extends AbstractPersistable<BigDecimal> {

    /** コメント */
    private String comment;

    /** ユーザーID */
    private BigDecimal userId;

    /** トークテーマID */
    private BigDecimal talkThemeId;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 入力日時 */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

}
