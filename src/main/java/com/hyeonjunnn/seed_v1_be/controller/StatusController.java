package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.status.service.StatusService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/status")
@Tag(name = "Status", description = "상태 관련 API")
public class StatusController {
    private final StatusService statusService;

    @GetMapping()
    @Operation(summary = "상태 목록 조회", description = "전체 상태의 목록을 조회한다.")
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
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<StatusResponseDto>> getStatuses () {

        List<StatusResponseDto> statusResponseDtos
                = statusService.getStatuses();

        if (!statusResponseDtos.isEmpty()) {
            return ResponseEntity.ok(statusResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping()
    @Operation(summary = "접두어 상태 목록 조회", description = "접두어로 상태의 목록을 조회한다.")
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
    public ResponseEntity<List<StatusResponseDto>> getStatusesByPrefix (
            @RequestBody String prefix) {

        List<StatusResponseDto> statusResponseDtos
                = statusService.getStatusesByPrefix(prefix);

        if (!statusResponseDtos.isEmpty()) {
            return ResponseEntity.ok(statusResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    @Operation(summary = "상태 추가", description = "상태를 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StatusResponseDto.class)
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
    public ResponseEntity<StatusResponseDto> createStatus (
            @RequestBody StatusRequestDto statusRequestDto) {


        statusService.saveStatus(statusRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Operation(summary = "상태 수정", description = "상태를 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StatusResponseDto.class)
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
    public ResponseEntity<StatusResponseDto> updateStatus (
            @RequestBody StatusRequestDto statusRequestDto) {

        statusService.updateStatus(statusRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    @Operation(summary = "상태 삭제", description = "상태를 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StatusResponseDto.class)
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
    public ResponseEntity<StatusResponseDto> deleteStatus (
            @RequestBody StatusRequestDto statusRequestDto) {

        statusService.deleteStatus(statusRequestDto);

        return ResponseEntity.noContent().build();
    }
}