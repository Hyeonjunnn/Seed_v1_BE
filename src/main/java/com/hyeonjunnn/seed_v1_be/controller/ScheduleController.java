package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.auth.entity.CustomUserDetails;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.service.ScheduleService;
import com.hyeonjunnn.seed_v1_be.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping()
    @Operation(summary = "일정 목록 조회", description = "전체 일정의 목록을 조회한다.")
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
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules () {

        List<ScheduleResponseDto> scheduleResponseDto = scheduleService.getSchedules();

        if (!scheduleResponseDto.isEmpty()) {
            return ResponseEntity.ok(scheduleResponseDto);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{scheduleNo}")
    @Operation(summary = "일정 단건 조회", description = "단건 일정의 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ScheduleResponseDto.class)
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
    public ResponseEntity<ScheduleResponseDto> getSchedule (@PathVariable Long scheduleNo) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.getSchedule(scheduleNo);

        return ResponseEntity.ok(scheduleResponseDto);
    }

    @PostMapping()
    @Operation(summary = "일정 추가", description = "일정을 추가한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ScheduleResponseDto.class)
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
    public ResponseEntity<ScheduleResponseDto> createSchedule (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @RequestBody ScheduleRequestDto scheduleRequestDto) {

        User user = customUserDetails.getUser();

        scheduleService.createSchedule(user, scheduleRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{scheduleNo}")
    @Operation(summary = "일정 수정", description = "일정을 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ScheduleResponseDto.class)
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
    public ResponseEntity<ScheduleResponseDto> updateSchedule (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long scheduleNo, @RequestBody ScheduleRequestDto scheduleRequestDto) {

        User user = customUserDetails.getUser();

        scheduleService.updateSchedule(user, scheduleNo, scheduleRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{scheduleNo}")
    @Operation(summary = "일정 삭제", description = "일정을 삭제한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ScheduleResponseDto.class)
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
    public ResponseEntity<ScheduleResponseDto> deleteSchedule (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @PathVariable Long scheduleNo) {

        User user = customUserDetails.getUser();

        scheduleService.deleteSchedule(user, scheduleNo);

        return ResponseEntity.noContent().build();
    }
}