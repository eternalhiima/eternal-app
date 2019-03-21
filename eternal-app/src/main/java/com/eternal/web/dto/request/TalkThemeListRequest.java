package com.eternal.web.dto.request;

import java.math.BigDecimal;
import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;
import lombok.Builder;
import lombok.Getter;

/**
 * TalkThemeListRequest
 *
 * @author taiki0304
 */
@Builder
@Getter
public class TalkThemeListRequest {

    /** 取得数 */
    private BigDecimal count;

    /** 最大取得数 */
    private BigDecimal maxCount;

    /** カテゴリID */
    private BigDecimal categoryId;

    /** ソートキー */
    private SortKeyType sortKey;

    /** ソート順 */
    private SortType sort;

    /**
     * TalkThemeListRequestを生成
     *
     * @param count 取得数
     * @param maxCount 最大取得数
     * @param categoryId カテゴリID
     * @param sortKey ソートキー
     * @param sort ソート順
     * @return TalkThemeListRequest
     */
    public static TalkThemeListRequest of(BigDecimal count, BigDecimal maxCount, BigDecimal categoryId, String sortKey,
            String sort) {
        SortKeyType sortKeyType = SortKeyType.get(sortKey)
                .orElseThrow(() -> new IllegalArgumentException(MessageCode.TYPW_MISMATCH_FIELDS));
        SortType sortType = SortType.get(sort)
                .orElseThrow(() -> new IllegalArgumentException(MessageCode.TYPW_MISMATCH_FIELDS));
        return TalkThemeListRequest.builder()
                .count(count)
                .maxCount(maxCount)
                .categoryId(categoryId)
                .sortKey(sortKeyType)
                .sort(sortType)
                .build();
    }
}
