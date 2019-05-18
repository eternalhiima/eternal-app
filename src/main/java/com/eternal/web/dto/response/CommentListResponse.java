package com.eternal.web.dto.response;

import java.util.List;
import com.eternal.web.dto.CommentDto;
import lombok.Data;

/**
 * {@link CommentListResponse}
 *
 * @author taiki0304
 */
@Data
public class CommentListResponse {

    /** コメントリスト */
    private List<CommentDto> commentList;
}
