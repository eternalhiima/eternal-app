/**
 *
 */
package com.eternal.web.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
 * @author taiki0304
 *
 */
@Component
@RequiredArgsConstructor
public class TalkThemeConverter {

    /** メッセージソース */
    private final MessageSourceImpl messageSource;

    /**
     * Ref001_トークテーマ一覧取得用のレスポンスコンバーター
     *
     * @param entityList
     * @return
     */
    public TalkThemeListResponse convert(List<TalkThemeEntity> entityList) {
        BigDecimal order = BigDecimal.ZERO;
        return TalkThemeListResponse.builder()
                .talkThemeList(entityList.stream()
                        .map(entity -> TalkThemeDto.builder()
                                .talkThemeId(entity.getId())
                                .order(order.add(BigDecimal.ONE))
                                .title(entity.getTitle())
                                .content(entity.getContent())
                                .thumbnailPath(entity.getThumbnailPath())
                                .goodCount(entity.getGoodCount())
                                .badCount(entity.getBadCount())
                                .talkedCount(entity.getTalkCount())
                                .categoryList(entity.getCategoryList().stream()
                                        .map(category -> CategoryDto.builder()
                                                .categoryId(category.getId())
                                                .categoryName(category.getCategoryName())
                                                .build())
                                        .collect(Collectors.toList()))
                                .postUser(entity.getUser().getUserName())
                                .postDateTime(entity.getCreateDatetime())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Upd001_トークテーマ投稿用のレスポンスコンバーター
     *
     * @param entity TalkThemeEntity
     * @return PostTalkResponse
     */
    public PostTalkResponse convert(TalkThemeEntity entity) {
        if (Objects.nonNull(entity)) {
            return PostTalkResponse.builder().isSuccess(Boolean.TRUE)
                    .message(messageSource.getMessage(MessageCode.POST_TALK_SUCCESS)).talkThemeId(entity.getId())
                    .build();
        }
        return PostTalkResponse.builder().isSuccess(Boolean.FALSE)
                .message(messageSource.getMessage(MessageCode.POST_TALK_FAILURE)).build();
    }
}
