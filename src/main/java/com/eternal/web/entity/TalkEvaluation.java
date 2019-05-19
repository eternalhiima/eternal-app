package com.eternal.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TALK_EVALUATION")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TalkEvaluation extends AbstractEntity {

    /** ユーザーID */
    private String userId;

    /** トークテーマID */
    private String talkThemeId;

    /** 評価 */
//    TODO: ENUMにする
    private int evaluation;

}
