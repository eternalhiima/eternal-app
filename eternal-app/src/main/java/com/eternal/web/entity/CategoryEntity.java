package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.eternal.web.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORY")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity extends AbstractEntity {

    /** カテゴリ名称 */
    private String categoryName;

    /** ユーザー */
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = UserEntity.class)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 使用回数 */
    private BigDecimal usedCount;

    /** 最終使用日時 */
    private LocalDateTime lastUsedDatetime;

    public static CategoryEntity of(CategoryDto dto, UserEntity user) {
        CategoryEntity entity = new CategoryEntity();
        entity.id = dto.getCategoryId();
        entity.categoryName = dto.getCategoryName();
        entity.user = user;
        entity.createDatetime = LocalDateTime.now();
        entity.inputDatetime = LocalDateTime.now();
        entity.updateDatetime = LocalDateTime.now();
        return entity;
    }

}
