/**
 *
 */
package com.eternal.web.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.TalkThemeDto;
import com.eternal.web.dto.response.PostTalkResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.entity.TalkThemeEntity;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import lombok.RequiredArgsConstructor;

/**
 * トークテーマコンバーター
 *
 * @author taiki0304
 */
@Component
@RequiredArgsConstructor
public class TalkThemeConverter {

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /**
     * Ref001_トークテーマ一覧取得用のレスポンスコンバーター
     *
     * @param entityList {@link TalkThemeEntity}
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
     * @param entity {@link TalkThemeEntity}
     * @return {@link PostTalkResponse}
     */
    public PostTalkResponse convert(TalkThemeEntity entity) {
        if (Objects.nonNull(entity)) {
            return PostTalkResponse.builder()
                    .isSuccess(true)
                    .message(messageSource.getMessage(MessageCode.POST_TALK_SUCCESS)).talkThemeId(entity.getId())
                    .build();
        }
        return PostTalkResponse.builder()
                .isSuccess(false)
                .message(messageSource.getMessage(MessageCode.POST_TALK_FAILURE)).build();
    }
}
