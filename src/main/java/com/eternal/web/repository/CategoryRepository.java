/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.CategoryEntity;

/**
 * {@link CategoryRepository}
 *
 * @author taiki0304
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /**
     * categoryNameを含む検索結果を{@link Page}として返却する
     *
     * @param categoryName
     * @param {@link Pageable} pageable
     * @return {@link Page}
     */
    Page<CategoryEntity> findByCategoryNameContaining(String categoryName, Pageable pageable);
}
