package com.hyeonjunnn.seed_v1_be.domain.comment.service;

import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    void saveComment(User user, Long boardNo, CommentRequestDto commentRequestDto);

    CommentResponseDto getComment(Long commentNo);

//    List<CommentResponseDto> getComments();

    Page<CommentResponseDto> getCommentsByBoardNo(Pageable pageable, Long boardNo);

    void updateComment(User user, Long boardNo, Long commentNo, CommentRequestDto commentRequestDto);

    void deleteComment(User user, Long boardNo, Long commentNo);
}
