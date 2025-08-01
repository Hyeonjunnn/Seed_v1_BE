package com.hyeonjunnn.seed_v1_be.domain.comment.service;

import com.hyeonjunnn.seed_v1_be.domain.board.repository.BoardRepository;
import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.repository.CommentRepository;
import com.hyeonjunnn.seed_v1_be.entity.Board;
import com.hyeonjunnn.seed_v1_be.entity.Comment;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public void saveComment(User user, Long boardNo, CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .parentCommentNo(commentRequestDto.getParentCommentNo())
                .board(board)
                .user(user)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public CommentResponseDto getComment(Long commentNo) {
        CommentResponseDto commentResponseDto = commentRepository.findById(commentNo)
                .map(CommentResponseDto::new).orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        return commentResponseDto;
    }

    @Override
    public Page<CommentResponseDto> getCommentsByBoardNo(Pageable pageable, Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        Page<CommentResponseDto> commentResponseDtos = commentRepository.findCommentsByBoard_BoardNo(pageable, boardNo)
                .map(CommentResponseDto::new);

        return commentResponseDtos;
    }

    @Transactional
    @Override
    public void updateComment(User user, Long boardNo, Long commentNo, CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        if (!(user.getUserNo() == comment.getUser().getUserNo())) {
            throw new RuntimeException("댓글 작성자가 아닙니다.");
        }

        comment.setContent(commentRequestDto.getContent());
    }

    @Transactional
    @Override
    public void deleteComment(User user, Long boardNo, Long commentNo) {
        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        if (!(user.getUserNo() == comment.getUser().getUserNo())) {
            throw new RuntimeException("댓글 작성자가 아닙니다.");
        }

        comment.setContent("작성자에 의해 삭제된 댓글입니다.");
    }
}
