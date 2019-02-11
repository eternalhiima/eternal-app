/**
 *
 */
package com.eternal.web.repository;

import java.math.BigDecimal;
import java.util.Optional;
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

    public Optional<UserEntity> findByUserName(String userName);
}
