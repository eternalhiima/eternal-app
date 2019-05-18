package com.eternal.web.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eternal.web.aop.ApiLog;
import com.eternal.web.config.WebApiEndPoint;
import com.eternal.web.dto.request.CommentListRequest;
import com.eternal.web.dto.request.PostCommentRequest;
import com.eternal.web.dto.request.TalkThemeListRequest;
import com.eternal.web.dto.response.CommentListResponse;
import com.eternal.web.dto.response.PostCommentResponse;
import com.eternal.web.dto.response.TalkThemeListResponse;
import com.eternal.web.service.CommentService;
import com.eternal.web.type.ApiComponentType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebApiEndPoint.ROOT_END_POINT)
public class CommentController {

    /** {@link CommentService} */
    private final CommentService commentService;

    /**
     * Ref003_コメント一覧取得
     *
     * @param {@link TalkThemeListRequest}
     * @return {@link TalkThemeListResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.REF003)
    @RequestMapping(value = WebApiEndPoint.COMMENTIST_END_POINT, method = RequestMethod.GET)
    public CommentListResponse getCategoryList(@Validated CommentListRequest request) {
        return commentService.getCommentList(request);
    }

    /**
     * Upd003_コメント投稿
     *
     * @param {@link PostCommentRequest} リクエスト
     * @return {@link PostCommentResponse}
     */
    @ApiLog(apiComponentType = ApiComponentType.UPD003)
    @RequestMapping(value = WebApiEndPoint.POSTCOMMENT_END_POINT, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostCommentResponse postTalkTheme(@RequestBody @Validated PostCommentRequest request) {
        return commentService.postComment(request);
    }
}
