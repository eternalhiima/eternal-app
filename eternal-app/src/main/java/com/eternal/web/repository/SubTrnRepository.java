package com.eternal.web.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.SubTrnEntity;

@Repository
public interface SubTrnRepository extends JpaRepository<SubTrnEntity, BigDecimal> {

}
