/**
 *
 */
package com.eternal.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eternal.web.entity.Comment;

/**
 * CommentRepository
 *
 * @author taiki0304
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
