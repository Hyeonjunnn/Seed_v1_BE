package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.service.BoardCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board-category")
@Tag(name = "BoardCategory", description = "게시판 카테고리 관련 API")
public class BoardCategoryController {
    private final BoardCategoryService boardCategoryService;

    @GetMapping()
    @Operation(summary = "게시판 카테고리 목록 조회", description = "전체 게시판 카테고리의 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<BoardCategoryResponseDto>> getBoard_categories () {
        List<BoardCategoryResponseDto> boardCategoryResponseDtos =  boardCategoryService.getBoard_categories();

        if (!boardCategoryResponseDtos.isEmpty()) {
            return ResponseEntity.ok(boardCategoryResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    @SecurityRequirement(name = "bearer-auth")
    @Operation(summary = "게시판 카테고리 추가", description = "게시판 카테고리를 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<BoardResponseDto> createBoardCategory (
            @RequestBody BoardCategoryRequestDto boardCategoryRequestDto) {

        boardCategoryService.saveBoardCategory(boardCategoryRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{boardCategoryNo}")
    @Operation(summary = "게시판 카테고리 수정", description = "게시판 카테고리를 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<BoardCategoryResponseDto> updateBoardCategory (
            @PathVariable Long boardCategoryNo
            , @RequestBody BoardCategoryRequestDto boardCategoryRequestDto) {

        boardCategoryService.updateBoardCategory(boardCategoryNo, boardCategoryRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{boardCategoryNo}")
    @Operation(summary = "게시판 카테고리 삭제", description = "게시판 카테고리를 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<BoardCategoryResponseDto> deleteBoardCategory (
            @PathVariable Long boardCategoryNo) {
        boardCategoryService.deleteBoardCategory(boardCategoryNo);

        return ResponseEntity.noContent().build();
    }
}
