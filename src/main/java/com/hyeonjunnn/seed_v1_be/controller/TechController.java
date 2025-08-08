package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.tech.service.TechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/tech")
@Tag(name = "Tech", description = "기술 관련 API")
public class TechController {
    private final TechService techService;

    @GetMapping()
    @Operation(summary = "기술 목록 조회", description = "전체 기술의 목록을 조회한다.")
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
    public ResponseEntity<List<TechResponseDto>> getTechs () {

        List<TechResponseDto> techResponseDtos = techService.getTechs();

        if (!techResponseDtos.isEmpty()) {
            return ResponseEntity.ok(techResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    @Operation(summary = "기술 추가", description = "기술을 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechResponseDto.class)
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
    public ResponseEntity<TechResponseDto> createTech (
            @RequestBody TechRequestDto techRequestDto) {

        techService.saveTech(techRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{techNo}")
    @Operation(summary = "기술 수정", description = "기술을 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechResponseDto.class)
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
    public ResponseEntity<TechResponseDto> updateTech (
            @PathVariable Long techNo, @RequestBody TechRequestDto techRequestDto) {

        techService.updateTech(techNo, techRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{techNo}")
    @Operation(summary = "기술 삭제", description = "기술을 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechResponseDto.class)
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
    public ResponseEntity<TechResponseDto> deleteTech (
            @PathVariable Long techNo) {

        techService.deleteTech(techNo);

        return ResponseEntity.noContent().build();
    }
}