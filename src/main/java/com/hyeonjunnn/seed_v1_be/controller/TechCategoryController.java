package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.service.TechCategoryService;
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
@RequestMapping("/api/techCategory")
@Tag(name = "TechCategory", description = "기술 카테고리 관련 API")
public class TechCategoryController {
    private final TechCategoryService techCategoryService;

    @GetMapping()
    @Operation(summary = "기술 카테고리 목록 조회", description = "전체 기술 카테고리의 목록을 조회한다.")
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
    public ResponseEntity<List<TechCategoryResponseDto>> getTechCategories () {


        List<TechCategoryResponseDto> techCategoryResponseDtos
                = techCategoryService.getTechCategories();

        if (!techCategoryResponseDtos.isEmpty()) {
            return ResponseEntity.ok(techCategoryResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    @Operation(summary = "기술 카테고리 추가", description = "기술 카테고리를 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechCategoryResponseDto.class)
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
    public ResponseEntity<TechCategoryResponseDto> createTechCategory (
            @RequestBody TechCategoryRequestDto techCategoryRequestDto) {

        techCategoryService.saveTechCategory(techCategoryRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{techCategoryNo}")
    @Operation(summary = "기술 카테고리 수정", description = "기술 카테고리를 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechCategoryResponseDto.class)
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
    public ResponseEntity<TechCategoryResponseDto> updateTechCategory (
            @PathVariable Long techCategoryNo
            , @RequestBody TechCategoryRequestDto techCategoryRequestDto) {

        techCategoryService.updateTechCategory(techCategoryNo, techCategoryRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{techCategoryNo}")
    @Operation(summary = "기술 카테고리 삭제", description = "기술 카테고리를 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TechCategoryResponseDto.class)
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
    public ResponseEntity<TechCategoryResponseDto> deleteTechCategory (
            @PathVariable Long techCategoryNo) {

        techCategoryService.deleteTechCategory(techCategoryNo);

        return ResponseEntity.noContent().build();
    }
}