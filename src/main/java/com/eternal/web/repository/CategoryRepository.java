/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.CategoryEntity;

/**
 * CategoryRepository
 *
 * @author taiki0304
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
