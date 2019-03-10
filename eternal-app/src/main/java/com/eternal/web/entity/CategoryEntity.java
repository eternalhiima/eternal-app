package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity extends AbstractEntity {

    /** カテゴリ名称 */
    private String categoryName;

    /** ユーザー */
    private Long userId;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 使用回数 */
    private BigDecimal usedCount;

    /** 最終使用日時 */
    private LocalDateTime lastUsedDatetime;

    public static CategoryEntity of(CategoryDto dto, Long userId) {
        CategoryEntity entity = new CategoryEntity();
        entity.id = dto.getCategoryId();
        entity.categoryName = dto.getCategoryName();
        entity.userId = userId;
        entity.createDatetime = LocalDateTime.now();
        entity.usedCount = BigDecimal.ZERO;
        entity.lastUsedDatetime = LocalDateTime.now();
        return entity;
    }

}
