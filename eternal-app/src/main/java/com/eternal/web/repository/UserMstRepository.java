package com.eternal.web.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.UserMstEntity;

@Repository
public interface UserMstRepository extends JpaRepository<UserMstEntity, BigDecimal> {

}
