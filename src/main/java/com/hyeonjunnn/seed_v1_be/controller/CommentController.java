package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.auth.entity.CustomUserDetails;
import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.dto.CommentResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.comment.service.CommentService;
import com.hyeonjunnn.seed_v1_be.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/board/{boardNo}/comment")
    @Operation(summary = "게시판 댓글 조회", description = "게시판 댓글을 조회한다.")
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
    public ResponseEntity<Page<CommentResponseDto>> getCommentsByBoardNo(
            @ParameterObject
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "commentNo") Pageable pageable
            , @PathVariable Long boardNo) {

        Page<CommentResponseDto> commentResponseDtos =  commentService.getCommentsByBoardNo(pageable, boardNo);

        if (!commentResponseDtos.isEmpty()) {
            return ResponseEntity.ok(commentResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/board/{boardNo}/comment")
    @Operation(summary = "게시판 댓글 생성", description = "게시판 댓글을 생성한다.")
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
    public ResponseEntity<BoardResponseDto> createComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long boardNo
            , @RequestBody CommentRequestDto commentRequestDto){
        User user = customUserDetails.getUser();

        commentService.saveComment(user, boardNo, commentRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/board/{boardNo}/comment/{commentNo}")
    @Operation(summary = "게시판 댓글 수정", description = "게시판 댓글을 수정한다.")
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
    public ResponseEntity<BoardResponseDto> updateBoardComment (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long boardNo
            , @PathVariable Long commentNo
            , @RequestBody CommentRequestDto commentRequestDto) {

        User user = customUserDetails.getUser();

        commentService.updateComment(user, boardNo, commentNo, commentRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/board/{boardNo}/comment/{commentNo}")
    @Operation(summary = "게시판 댓글 삭제", description = "게시판 댓글을 삭제한다.")
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
    public ResponseEntity<BoardResponseDto> deleteBoardComment (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long boardNo
            , @PathVariable Long commentNo) {

        User user = customUserDetails.getUser();

        commentService.deleteComment(user, boardNo, commentNo);

        return ResponseEntity.noContent().build();
    }
}
