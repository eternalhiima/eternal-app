package com.eternal.web.dto.response;

import java.util.List;
import com.eternal.web.dto.TalkThemeDto;
import lombok.Data;

/**
 * TalkThemeListResponse
 *
 * @author taiki0304
 */
@Data
public class TalkThemeListResponse {

    /** トークテーマリスト */
    private List<TalkThemeDto> talkThemeList;
}
