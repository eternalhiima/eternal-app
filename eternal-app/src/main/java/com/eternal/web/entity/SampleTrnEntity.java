package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Table(name="sample_trn")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SampleTrnEntity extends AbstractPersistable<BigDecimal> {

    /** サンプルデータ名 */
    private String sampleDataName;
    /** デリートフラグ */
    private int isDeleted;
    /** サンプルデータ1 */
    private BigDecimal sampleData1;
    /** ユーザーID */
    private BigDecimal userId;
    /** サンプル日付 */
    private LocalDate sampleDate;
    /** サンプル日時 */
    private LocalDateTime sample_datetime;
    /** 作成ユーザー */
    private String create_user;
    /** 更新ユーザー */
    private String modifyUser;
    /** 作成日時 */
    private LocalDateTime createdDatetime;
    /** 更新日時 */
    private LocalDateTime modifiedDatetime;

}
