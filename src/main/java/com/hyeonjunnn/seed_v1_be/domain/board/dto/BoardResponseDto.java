package com.hyeonjunnn.seed_v1_be.domain.board.dto;

import com.hyeonjunnn.seed_v1_be.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardNo;

    private String title;

    private String content;

    private boolean isDeleted;

    private String boardCategoryName;

    private Long userNo;

    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BoardResponseDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardCategoryName = board.getBoardCategory().getName();
        this.userNo = board.getUser().getUserNo();
        this.userName = board.getUser().getName();
        this.isDeleted = board.getIsDeleted();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }
}
