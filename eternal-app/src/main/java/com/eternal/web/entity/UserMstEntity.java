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
@Table(name = "user_master")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMstEntity extends AbstractPersistable<BigDecimal> {

    /** ユーザー名 */
    private String userName;
    /** パスワード */
    private String password;
    /** デリートフラグ */
    private int isDeleted;
    /** 作成ユーザー */
    private String create_user;
    /** 更新ユーザー */
    private String modifyUser;
    /** 作成日時 */
    private LocalDateTime createdDatetime;
    /** 更新日時 */
    private LocalDateTime modifiedDatetime;

}
