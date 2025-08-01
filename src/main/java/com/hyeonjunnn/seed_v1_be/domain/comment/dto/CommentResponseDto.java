package com.hyeonjunnn.seed_v1_be.domain.comment.dto;

import com.hyeonjunnn.seed_v1_be.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentNo;

    private String content;

    private Long parentCommentNo;

    private Long boardNo;

    private Long userNo;

    private String userName;

    private Instant createdAt;

    private Instant updatedAt;

    public CommentResponseDto(Comment comment) {
        this.commentNo = comment.getCommentNo();
        this.content = comment.getContent();
        this.parentCommentNo = comment.getParentCommentNo();
        this.boardNo = comment.getCommentNo();
        this.userNo = comment.getUser().getUserNo();
        this.userName = comment.getUser().getName();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
