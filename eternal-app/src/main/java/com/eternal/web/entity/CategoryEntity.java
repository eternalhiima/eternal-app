package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.eternal.web.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "CATEGORY")
@Builder
@Getter
@AllArgsConstructor
public class CategoryEntity extends AbstractPersistable<BigDecimal> {

    /** カテゴリ名称 */
    private String categoryName;

    /** ユーザーID */
    private BigDecimal userId;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 使用回数 */
    private BigDecimal usedCount;

    /** 最終使用日時 */
    private LocalDateTime lastUsedDatetime;

    /** 入力日時 */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

    private CategoryEntity() {
    }

    public static CategoryEntity of(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getCategoryId());
        entity.categoryName = dto.getCategoryName();
        return entity;
    }

}
