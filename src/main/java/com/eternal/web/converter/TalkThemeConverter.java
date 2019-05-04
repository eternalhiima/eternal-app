package com.eternal.web.converter;

import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.TalkThemeDto;
import com.eternal.web.dto.response.EvalTalkResponse;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.TalkThemeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * {@link TalkThemeConverter}
 *
 * @author taiki0304
 */
@Component
@RequiredArgsConstructor
public class TalkThemeConverter {

    /**
     * Ref001_トークテーマ一覧取得用のレスポンスコンバーター
     *
     * @param {@link TalkThemeEntity} entityList
     * @return {@link TalkThemeListResponse}
     */
    public TalkThemeListResponse convert(List<TalkThemeEntity> entityList) {
        AtomicInteger order = new AtomicInteger();
        TalkThemeListResponse response = new TalkThemeListResponse();
        response.setTalkThemeList(entityList.stream().map(e -> TalkThemeDto.builder()
                .talkThemeId(e.getId())
                .order(BigDecimal.valueOf(order.getAndIncrement()))
                .title(e.getTitle())
                .content(e.getContent())
                .thumbnailPath(e.getThumbnailPath())
                .goodCount(e.getGoodCount())
                .badCount(e.getBadCount())
                .talkedCount(e.getTalkCount())
                .categoryList(e.getCategoryList().stream()
                        .map(c -> new CategoryDto(c.getId(), c.getCategoryName()))
                        .collect(Collectors.toList()))
                .postDateTime(e.getCreateDatetime()).build())
                .collect(Collectors.toList()));
        return response;
    }

    /**
     * Upd001_トークテーマ投稿用のレスポンスコンバーター
     *
     * @param {@link TalkThemeEntity} entity
     * @return {@link PostTalkResponse}
     */
    public PostTalkResponse convert(TalkThemeEntity entity, String message) {
        return PostTalkResponse.builder()
                .message(message)
                .talkThemeId(entity.getId())
                .build();
    }

    /**
     * Upr002_トークテーマ評価用のコンバーター
     *
     * @param {@link TalkThemeEntity} entity
     * @return {@linkEvalTalkResponse}
     */
    public EvalTalkResponse convertEval(TalkThemeEntity entity) {
        return EvalTalkResponse.builder()
                .talkThemeId(entity.getId())
                .goodCount(entity.getGoodCount())
                .badCount(entity.getBadCount())
                .build();
    }
}
