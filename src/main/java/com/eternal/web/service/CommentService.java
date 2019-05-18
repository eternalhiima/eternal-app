package com.eternal.web.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.CommentConverter;
import com.eternal.web.dto.request.CommentListRequest;
import com.eternal.web.dto.request.PostCommentRequest;
import com.eternal.web.dto.response.CommentListResponse;
import com.eternal.web.dto.response.PostCommentResponse;
import com.eternal.web.entity.Comment;
import com.eternal.web.entity.User;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

/**
 * {@link CommentService}
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    /** {@link CommentRepository} */
    private final CommentRepository commentRepository;

    /** {@link UserService} */
    private final UserService userService;

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /** {@link CommentConverter} */
    private final CommentConverter converter;

    /**
     * Ref003_コメント一覧取得
     *
     * @param {@link CommentListRequest} request
     * @return {@link CommentListResponse}
     */
    public CommentListResponse getCommentList(CommentListRequest request) {
        List<Comment> commentList = commentRepository
                .findByTalkThemeId(request.getTalkThemeId(), PageRequest.of(request.getPage(), request.getSize()))
                .getContent();
        return converter.convert(commentList);
    }

    /**
     * Upd003_コメント投稿
     *
     * @param {@link PostCommentRequest} request
     * @return {@link PostCommentResponse}
     */
    @AppLog
    @Transactional
    public PostCommentResponse postComment(PostCommentRequest request) {
        User user = Objects.nonNull(request.getUserId())
                // ユーザーを指定した場合はDBよりユーザーを検索
                ? userService.findById(request.getUserId())
                // ユーザーID未指定の場合はユーザーを新規に作成
                : userService.addUser(request.getUserName());

        // Commentを作成しDBに保存
        Comment comment =
                commentRepository.saveAndFlush(Comment.of(request.getTalkThemeId(), user, request.getComment()));
        String messageCode = Objects.nonNull(comment) ? MessageCode.POST_SUCCESS : MessageCode.POST_FAILURE;
        String message = messageSource.getMessage(messageCode, Arrays.asList("コメント"));
        return converter.convert(message, comment);
    }
}
