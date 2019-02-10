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
@Table(name = "TALK_HISTORY")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TalkHistoryEntity extends AbstractPersistable<BigDecimal> {

    /** ユーザーID */
    private String userId;

    /** トークテーマID */
    private String talkThemeId;

    /** 開始時刻 */
    private LocalDateTime startDatetime;

    /** 終了時刻 */
    private LocalDateTime endDatetime;

    /** 入力日時? */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

}
