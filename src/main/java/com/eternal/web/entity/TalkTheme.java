package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.eternal.web.dto.request.PostTalkRequest;
import com.eternal.web.type.EvaluationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TALK_THEME")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkTheme extends AbstractEntity {

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TALK_THEME_CATEGORY_BIND", joinColumns = @JoinColumn(name = "TALK_THEME_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Category> categoryList;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /**
     * トークテーマ投稿のリクエストよりEntityを生成する
     *
     * @param request {@link PostTalkRequest}
     * @param userId
     * @return {@link TalkTheme}
     */
    public static TalkTheme of(PostTalkRequest request, Long userId, List<Category> categoryList) {
        TalkTheme entity = new TalkTheme();
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
     * トークテーマを評価する
     *
     * @pram {@link EvaluationType} eval
     * @return {@link TalkTheme}
     */
    public TalkTheme eval(EvaluationType eval) {
        switch (eval) {
            case GOOD:
                goodCount = goodCount.add(BigDecimal.ONE);
                break;
            case BAD:
                badCount = badCount.add(BigDecimal.ONE);
                break;
        }
        return this;
    }
}
