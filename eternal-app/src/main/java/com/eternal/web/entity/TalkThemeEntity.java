package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.eternal.web.dto.request.PostTalkRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TALK_THEME")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkThemeEntity extends AbstractEntity {

    /** タイトル */
    private String title;

    /** 内容 */
    private String content;

    /** サムネイルパス */
    private String thumbnailPath;

    /** 関連URL */
    private String relatedUrl;

    /** ユーザー */
    private Long userId;

    /** GOOD数 */
    private BigDecimal goodCount;

    /** BAD数 */
    private BigDecimal badCount;

    /** トーク数 */
    private BigDecimal talkCount;

    /** カテゴリリスト */
    @ManyToMany
    @JoinTable(name = "TALK_THEME_CATEGORY_BIND", joinColumns = @JoinColumn(name = "TALK_THEME_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<CategoryEntity> categoryList;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /**
     * トークテーマ投稿のリクエストよりEntityを生成する
     *
     * @param request {@link PostTalkRequest}
     * @param userId
     * @return {@link TalkThemeEntity}
     */
    public static TalkThemeEntity of(PostTalkRequest request, Long userId, List<CategoryEntity> categoryList) {
        TalkThemeEntity entity = new TalkThemeEntity();
        entity.title = request.getTitle();
        entity.content = request.getContent();
        entity.relatedUrl = request.getRelatedUrl();
        entity.categoryList = categoryList;
        entity.userId = userId;
        entity.goodCount = BigDecimal.ZERO;
        entity.badCount = BigDecimal.ZERO;
        entity.talkCount = BigDecimal.ZERO;
        entity.createDatetime = LocalDateTime.now();
        return entity;
    }

    /**
     * トークテーマを高評価する
     *
     * @param entity
     * @return
     */
    public TalkThemeEntity evalGood(TalkThemeEntity entity) {
        // TODO: good数を増やしたトークテーマを返却する
        return null;
    }

    /**
     * トークテーマを低評価する
     *
     * @param entity
     * @return
     */
    public TalkThemeEntity evalBad(TalkThemeEntity entity) {
        // TODO: bad数を増やしたトークテーマを返却する
        return null;
    }


}
