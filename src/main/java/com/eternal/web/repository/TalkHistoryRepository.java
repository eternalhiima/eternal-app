/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.TalkHistory;

/**
 * TalkHistoryRepository
 *
 * @author taiki0304
 */
@Repository
public interface TalkHistoryRepository extends JpaRepository<TalkHistory, Long> {

}
