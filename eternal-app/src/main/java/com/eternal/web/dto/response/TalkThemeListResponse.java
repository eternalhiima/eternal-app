package com.eternal.web.dto.response;

import java.util.List;
import com.eternal.web.dto.TalkThemeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * TalkThemeListResponse
 *
 * @author taiki0304
 */
@AllArgsConstructor
@Builder
@Getter
public class TalkThemeListResponse {

    /** トークテーマリスト */
    private List<TalkThemeDto> talkThemeList;
}
