package com.eternal.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.TalkTheme;

/**
 * {@link TalkThemeRepository}
 *
 * @author taiki0304
 */
@Repository
public interface TalkThemeRepository extends JpaRepository<TalkTheme, Long> {
}
