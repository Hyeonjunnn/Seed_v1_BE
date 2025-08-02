package com.hyeonjunnn.seed_v1_be.domain.board.service;

import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.board.repository.BoardRepository;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.repository.BoardCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.Board;
import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    public void saveBoard(User user, BoardRequestDto boardRequestDto) {
        BoardCategory boardCategory = boardCategoryRepository.findById(boardRequestDto.getBoardCategoryNo())
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."));

        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .boardCategory(boardCategory)
                .user(user)
                .build();

        boardRepository.save(board);
    }

    @Override
    public BoardResponseDto getBoard(Long boardNo) {
        BoardResponseDto boardResponseDto =  boardRepository.findById(boardNo)
                .map(BoardResponseDto::new).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        return boardResponseDto;
    }

    @Override
    public Page<BoardResponseDto> getBoards(Pageable pageable, User user, Long boardCategoryNo, String boardTitle) {
        Page<BoardResponseDto> boardResponseDtos;

        if (user == null || !(user.getRole().getName().equals("ADMIN"))) {
            boardResponseDtos =
                    boardRepository.findBoardsByBoardCategory_BoardCategoryNoAndTitleContainsAndIsDeletedFalse(pageable, boardCategoryNo, boardTitle)
                            .map(BoardResponseDto::new);
        } else {
            boardResponseDtos =
                    boardRepository.findBoardsByBoardCategory_BoardCategoryNoAndTitleContains(pageable, boardCategoryNo, boardTitle)
                            .map(BoardResponseDto::new);
        }

        return boardResponseDtos;
    }

    @Transactional
    @Override
    public void updateBoard(User user, Long boardNo, BoardRequestDto boardRequestDto) {
        Board board =  boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        if (!user.getUserNo().equals(board.getUser().getUserNo())) {
            throw new RuntimeException("게시물 작성자가 아닙니다.");
        }

        BoardCategory boardCategory = boardCategoryRepository.findById(boardRequestDto.getBoardCategoryNo())
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."));

        board.setBoardCategory(boardCategory);
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
    }

    @Transactional
    @Override
    public void deleteBoard(User user, Long boardNo) {
        Board board =  boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        if (!user.getUserNo().equals(board.getUser().getUserNo())) {
            throw new RuntimeException("게시글 작성자가 아닙니다.");
        }

        board.setIsDeleted(true);
    }
}
