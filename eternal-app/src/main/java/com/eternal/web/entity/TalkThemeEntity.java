package com.eternal.web.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.eternal.web.dto.request.PostTalkRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "TALK_THEME")
@Getter
@Builder
@AllArgsConstructor
public class TalkThemeEntity extends AbstractPersistable<BigDecimal> {

    /** タイトル */
    private String title;

    /** 内容 */
    private String content;

    /** サムネイルパス */
    private String thumbnailPath;

    /** 関連URL */
    private String relatedUrl;

    /** ユーザー */
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = UserEntity.class)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    /** GOOD数 */
    private BigDecimal goodCount;

    /** BAD数 */
    private BigDecimal badCount;

    /** トーク数 */
    private BigDecimal talkedCount;

    /** カテゴリリスト */
    @ManyToMany
    @JoinTable(name = "TALK_THEME_CATEGORY_BIND", joinColumns = @JoinColumn(name = "TALK_THEME_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<CategoryEntity> categoryList;

    /** 作成日時 */
    private LocalDateTime createDatetime;

    /** 入力日時 */
    private LocalDateTime inputDatetime;

    /** 更新日時 */
    private LocalDateTime updateDatetime;

    private TalkThemeEntity() {}

    /**
     * トークテーマ投稿のリクエストよりEntityを生成する
     *
     * @param request トークテーマ投稿リクエスト
     * @return TalkThemeEntity
     */
    public static TalkThemeEntity of(PostTalkRequest request, UserEntity user) {
        TalkThemeEntity entity = new TalkThemeEntity();
        entity.title = request.getTitle();
        entity.content = request.getContent();
        entity.relatedUrl = request.getRelatedUrl();
        entity.categoryList = request.getCategoryList().stream()
                .map(dto -> CategoryEntity.of(dto, user))
                .collect(Collectors.toList());
        entity.user = user;
        entity.createDatetime = LocalDateTime.now();
        entity.inputDatetime = LocalDateTime.now();
        entity.updateDatetime = LocalDateTime.now();
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
