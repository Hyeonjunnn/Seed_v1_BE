package com.hyeonjunnn.seed_v1_be.domain.comment.repository;

import com.hyeonjunnn.seed_v1_be.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findCommentsByBoard_BoardNo(Pageable pageable, Long boardNo);
}
