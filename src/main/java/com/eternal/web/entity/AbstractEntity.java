/**
 *
 */
package com.eternal.web.entity;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;

/**
 * Entityの基底クラス
 *
 * @author taiki0304
 */
@MappedSuperclass
@Getter
public abstract class AbstractEntity {

    /** Primary Key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /** 入力日時 */
    protected LocalDateTime inputDatetime;

    /** 更新日時 */
    protected LocalDateTime updateDatetime;

    /** save前に実行される */
    @PrePersist
    public void prePersist() {
        this.inputDatetime = LocalDateTime.now();
        this.updateDatetime = LocalDateTime.now();
    }

    /** update前に実行される */
    @PreUpdate
    public void preUpdate() {
        this.updateDatetime = LocalDateTime.now();
    }
}
