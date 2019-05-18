/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.Comment;

/**
 * {@link CommentRepository}
 *
 * @author taiki0304
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * talkThemeIdが一致する{@link Comment}を{@link Page}として返却する
     *
     * @param talkThemeId
     * @param {@link Pageable} pageable
     * @return {@link Page}
     */
    Page<Comment> findByTalkThemeId(Long talkThemeId, Pageable pageable);
}
