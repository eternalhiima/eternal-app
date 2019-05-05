package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.eternal.web.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORY")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends AbstractEntity {

    /** カテゴリ名称 */
    private String categoryName;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 使用回数 */
    private BigDecimal usedCount;

    /** 最終使用日時 */
    private LocalDateTime lastUsedDatetime;

    /**
     * カテゴリを新規に追加する
     * @param dto {@link CategoryDto}
     * @return {@link Category}
     */
    public static Category of(CategoryDto dto) {
        Category entity = new Category();
        entity.id = dto.getCategoryId();
        entity.categoryName = dto.getCategoryName();
        entity.createDatetime = LocalDateTime.now();
        entity.usedCount = BigDecimal.ZERO;
        entity.lastUsedDatetime = LocalDateTime.now();
        return entity;
    }

    /**
     * カテゴリの使用回数を増やす
     *
     * @return {@link Category}
     */
    public Category add() {
        this.usedCount = this.getUsedCount().add(BigDecimal.ONE);
        return this;
    }
}
