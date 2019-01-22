package com.eternal.web.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category
 *
 * @author taiki0304
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    /** カテゴリID */
    private BigDecimal id;

    /** カテゴリ名 */
    private String categoryName;

}
