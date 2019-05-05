package com.eternal.web.converter;

import org.springframework.stereotype.Component;
import com.eternal.web.dto.CommentDto;
import com.eternal.web.dto.response.PostCommentResponse;
import com.eternal.web.entity.Comment;
import com.eternal.web.entity.User;

/**
 * {@link CommentConverter}
 *
 * @author "taiki0304"
 */
@Component
public class CommentConverter {

    /**
     * ユーザー情報付きのUpd003_コメント投稿用のコンバーター
     *
     * @param message
     * @param {@link Comment} comment
     * @param {@link User} user
     * @return {@link PostCommentResponse}
     */
    public PostCommentResponse convert(String message, Comment comment, User user) {
        CommentDto dto = CommentDto.builder()
                .commentId(comment.getId())
                .comment(comment.getComment())
                .userId(user.getId())
                .userName(user.getUserName())
                .userThumbnailPath(user.getIconPath())
                .postDateTime(comment.getCreateDatetime())
                .build();
        return PostCommentResponse.builder()
                .message(message)
                .comment(dto)
                .build();
    }

    /**
     * ユーザー情報が無い場合のUpd003_コメント投稿用のコンバーター
     * @param message
     * @param {@link Comment} comment
     * @return {@link PostCommentResponse}
     */
    public PostCommentResponse convert(String message, Comment comment) {
        CommentDto dto = CommentDto.builder()
                .commentId(comment.getId())
                .comment(comment.getComment())
                .postDateTime(comment.getCreateDatetime())
                .build();
        return PostCommentResponse.builder()
                .message(message)
                .comment(dto)
                .build();
    }
}
