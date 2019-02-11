package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "USER")
@Getter
@AllArgsConstructor
public class UserEntity extends AbstractPersistable<BigDecimal> {

    /** ユーザー名 */
    private String userName;

    /** アイコンパス */
    private String iconPath;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 入力日時 */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

    private UserEntity() {
    }

    public static UserEntity of(String userName) {
        UserEntity entity = new UserEntity();
        entity.userName = userName;
        // TODO: アイコンの設定
        entity.createDatetime = LocalDateTime.now();
        entity.inputDatetime = LocalDateTime.now();
        entity.updateDatetime = LocalDateTime.now();
        return entity;
    }

}
