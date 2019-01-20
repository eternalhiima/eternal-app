package com.eternal.web.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.SampleTrnEntity;
@Repository
public interface SampleTrnRepository extends JpaRepository<SampleTrnEntity, BigDecimal> {

}
