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
@Table(name = "TALK_EVALUATION")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TalkEvaluationEntity extends AbstractPersistable<BigDecimal> {

    /** ユーザーID */
    private String userId;

    /** トークテーマID */
    private String talkThemeId;

    /** 評価 */
//    TODO: ENUMにする
    private int evaluation;

    /** 入力日時 */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

}
