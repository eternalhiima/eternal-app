package com.eternal.web.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TALK_HISTORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TalkHistory extends AbstractEntity {

    /** ユーザーID */
    private String userId;

    /** トークテーマID */
    private String talkThemeId;

    /** 開始時刻 */
    private LocalDateTime startDatetime;

    /** 終了時刻 */
    private LocalDateTime endDatetime;

}
