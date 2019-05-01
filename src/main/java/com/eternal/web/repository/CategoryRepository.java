/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.CategoryEntity;

import java.util.List;

/**
 * CategoryRepository
 *
 * @author taiki0304
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    public Page<CategoryEntity> findByCategoryNameContaining(String categoryName, Pageable pageable);

}
