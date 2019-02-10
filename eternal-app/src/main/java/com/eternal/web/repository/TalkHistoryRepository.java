/**
 *
 */
package com.eternal.web.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.TalkHistoryEntity;

/**
 * TalkHistoryRepository
 *
 * @author taiki0304
 */
@Repository
public interface TalkHistoryRepository extends JpaRepository<TalkHistoryEntity, BigDecimal> {

}
