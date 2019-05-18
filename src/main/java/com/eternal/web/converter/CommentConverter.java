package com.eternal.web.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.eternal.web.dto.CommentDto;
import com.eternal.web.dto.response.CommentListResponse;
import com.eternal.web.dto.response.PostCommentResponse;
import com.eternal.web.entity.Comment;
import com.eternal.web.entity.User;

/**
 * {@link CommentConverter}
 *
 * @author taiki0304
 */
@Component
public class CommentConverter {

    /**
     * Ref003_コメント一覧用のコンバーター
     *
     * @param commentList
     * @return　{@link CommentListResponse}
     */
    public CommentListResponse convert(List<Comment> commentList) {
        CommentListResponse response = new CommentListResponse();
        List<CommentDto> commentDtoList = commentList.stream().map(e -> CommentDto.builder()
                .commentId(e.getId())
                .comment(e.getComment())
                .userId(e.getUser().getId())
                .userName(e.getUser().getUserName())
                .userThumbnailPath(e.getUser().getIconPath())
                .postDateTime(e.getCreateDatetime())
                .build())
                .collect(Collectors.toList());
        response.setCommentList(commentDtoList);
        return response;
    }

    /**
     * Upd003_コメント投稿用のコンバーター
     *
     * @param message
     * @param {@link Comment} comment
     * @param {@link User} user
     * @return {@link PostCommentResponse}
     */
    public PostCommentResponse convert(String message, Comment comment) {
        CommentDto dto;
        if (Objects.isNull(comment.getUser())) {
            dto = CommentDto.builder()
                    .commentId(comment.getId())
                    .comment(comment.getComment())
                    .postDateTime(comment.getCreateDatetime())
                    .build();
        } else {
            dto = CommentDto.builder()
                    .commentId(comment.getId())
                    .comment(comment.getComment())
                    .userId(comment.getUser().getId())
                    .userName(comment.getUser().getUserName())
                    .userThumbnailPath(comment.getUser().getIconPath())
                    .postDateTime(comment.getCreateDatetime())
                    .build();
        }
        return PostCommentResponse.builder()
                .message(message)
                .comment(dto)
                .build();
    }
}
