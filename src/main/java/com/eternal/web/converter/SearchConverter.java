package com.eternal.web.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.eternal.web.dto.CategoryDto;
import com.eternal.web.dto.TalkThemeDto;
import com.eternal.web.dto.response.SearchResultListResponse;
import com.eternal.web.entity.TalkTheme;
import lombok.RequiredArgsConstructor;

/**
 * {@link SearchConverter}
 *
 * @author taiki0304
 */
@Component
@RequiredArgsConstructor
public class SearchConverter {

    /**
     * Ref001_トークテーマ一覧取得用のレスポンスコンバーター
     *
     * @param {@link TalkTheme} entityList
     * @return {@link SearchResultListResponse}
     */
    public SearchResultListResponse convert(List<TalkTheme> entityList) {
        AtomicInteger order = new AtomicInteger();
        SearchResultListResponse response = new SearchResultListResponse();
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
}
