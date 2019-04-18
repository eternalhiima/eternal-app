/**
 *
 */
package com.eternal.web.repository;

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
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByUserName(String userName);
}
