package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.auth.entity.CustomUserDetails;
import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.project.service.ProjectService;
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
@RequestMapping("/api/project")
@Tag(name = "Project", description = "프로젝트 관련 API")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping()
    @Operation(summary = "프로젝트 목록 조회", description = "전체 프로젝트의 목록을 조회한다.")
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
    public ResponseEntity<Page<ProjectResponseDto>> getProjects (
            @ParameterObject
            @PageableDefault(page = 0, size = 9, direction = Sort.Direction.DESC, sort = "projectNo") Pageable pageable
            , @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        User user = (customUserDetails != null) ? customUserDetails.getUser() : null;

        Page<ProjectResponseDto> projectResponseDtos = projectService.getProjects(user, pageable);

        if (!projectResponseDtos.isEmpty()) {
            return ResponseEntity.ok(projectResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{projectNo}")
    @Operation(summary = "프로젝트 단건 조회", description = "단건 프로젝트의 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjectResponseDto.class)
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
    public ResponseEntity<ProjectResponseDto> getProject (@PathVariable Long projectNo) {
        ProjectResponseDto projectResponseDto = projectService.getProject(projectNo);

        return ResponseEntity.ok(projectResponseDto);
    }

    @PostMapping()
    @Operation(summary = "프로젝트 추가", description = "프로젝트를 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjectResponseDto.class)
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
    public ResponseEntity<ProjectResponseDto> createProject (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @RequestBody ProjectRequestDto projectRequestDto) {

        User user = customUserDetails.getUser();

        projectService.saveProject(user, projectRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectNo}")
    @Operation(summary = "프로젝트 수정", description = "프로젝트를 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjectResponseDto.class)
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
    public ResponseEntity<ProjectResponseDto> updateProject (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long projectNo, @RequestBody ProjectRequestDto projectRequestDto) {

        User user = customUserDetails.getUser();

        projectService.updateProject(user, projectNo, projectRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{projectNo}")
    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjectResponseDto.class)
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
    public ResponseEntity<ProjectResponseDto> deleteProject (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long projectNo) {

        User user = customUserDetails.getUser();

        projectService.deleteProject(user, projectNo);

        return ResponseEntity.noContent().build();
    }
}