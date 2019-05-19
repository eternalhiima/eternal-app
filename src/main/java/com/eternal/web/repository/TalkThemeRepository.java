package com.eternal.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * タイトルにキーワードを含む検索結果を{@link Page}として返却する
     *
     * @param title タイトルを検索するキーワード
     * @param content 内容を検索するキーワード
     * @param {@link Pageable}
     * @return {@link Page}
     */
    Page<TalkTheme> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
