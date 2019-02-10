/**
 *
 */
package com.eternal.web.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.UserEntity;

/**
 * UserRepository
 *
 * @author taiki0304
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, BigDecimal> {

}
