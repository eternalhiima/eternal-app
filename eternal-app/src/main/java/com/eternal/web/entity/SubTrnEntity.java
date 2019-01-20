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
@Table(name = "sub_trn")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubTrnEntity extends AbstractPersistable<BigDecimal> {

    /** サンプルデータID */
    private BigDecimal sampleDataId;
    /** デリートフラグ */
    private int isDeleted;
    /** サブデータ名 */
    private String subDataName;
    /** 作成ユーザー */
    private String create_user;
    /** 更新ユーザー */
    private String modifyUser;
    /** 作成日時 */
    private LocalDateTime createdDatetime;
    /** 更新日時 */
    private LocalDateTime modifiedDatetime;
}
