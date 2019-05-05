package com.eternal.web.service;

import java.util.Arrays;
import java.util.Objects;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.eternal.web.aop.AppLog;
import com.eternal.web.converter.CommentConverter;
import com.eternal.web.dto.request.PostCommentRequest;
import com.eternal.web.dto.response.PostCommentResponse;
import com.eternal.web.entity.Comment;
import com.eternal.web.entity.User;
import com.eternal.web.exception.ServiceException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.CommentRepository;
import com.eternal.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    /** {@link CommentRepository} */
    private final CommentRepository commentRepository;

    /** {@link UserRepository} */
    private final UserRepository userRepository;

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /** {@link CommentConverter} */
    private final CommentConverter converter;

    /**
     * Upd003_コメント投稿
     *
     * @param {@link PostCommentRequest} request
     * @return {@link PostCommentResponse}
     */
    @AppLog
    public PostCommentResponse postComment(PostCommentRequest request) {
        // Commentを作成しDBに保存
        Comment comment = commentRepository
                .saveAndFlush(Comment.of(request.getTalkThemeId(), request.getUserId(), request.getComment()));
        String messageCode = Objects.nonNull(comment) ? MessageCode.POST_SUCCESS : MessageCode.POST_FAILURE;
        String message = messageSource.getMessage(messageCode, Arrays.asList("コメント"));
        if (Objects.nonNull(request.getUserId())) {
            // ユーザーを指定した場合
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_USER,
                            messageSource.getMessage(MessageCode.UNKNOWN_USER)));
            return converter.convert(message, comment, user);
        }
        return converter.convert(message, comment);
    }
}
