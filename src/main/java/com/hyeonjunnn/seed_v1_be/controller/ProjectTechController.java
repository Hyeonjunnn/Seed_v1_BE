package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.service.ProjectTechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projectTech")
@Tag(name = "ProjectTech", description = "프로젝트 기술 관련 API")
public class ProjectTechController {
    private final ProjectTechService projectTechService;

    @DeleteMapping("/{projectTechNo}")
    @Operation(summary = "프로젝트 기술 삭제", description = "프로젝트 기술을 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjectTechResponseDto.class)
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
    public ResponseEntity<ProjectTechResponseDto> deleteProjectTech (
            @RequestBody ProjectTechRequestDto projectTechRequestDto) {

        projectTechService.deleteProjectTech(projectTechRequestDto);

        return ResponseEntity.noContent().build();
    }
}