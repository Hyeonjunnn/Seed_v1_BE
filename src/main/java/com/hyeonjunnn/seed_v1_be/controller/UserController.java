package com.hyeonjunnn.seed_v1_be.controller;

import com.hyeonjunnn.seed_v1_be.domain.auth.entity.CustomUserDetails;
import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.user.service.UserService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    @GetMapping()
    @Operation(summary = "단건 유저 조회", description = "로그인한 유저 본인의 정보를 조회한다.")
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
    public ResponseEntity<UserResponseDto> getUser (
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = (customUserDetails != null) ? customUserDetails.getUser() : null;

        UserResponseDto userResponseDto = new UserResponseDto(user);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping()
    @Operation(summary = "유저 목록 조회", description = "전체 유저의 목록을 조회한다.")
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
    public ResponseEntity<Page<UserResponseDto>> getUsers (
            @ParameterObject
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC, sort = "usersNo") Pageable pageable
            , @RequestParam(required = false, defaultValue = "") String email) {

        Page<UserResponseDto> userResponseDtos = userService.getUsers(pageable, email);

        if (!userResponseDtos.isEmpty()) {
            return ResponseEntity.ok(userResponseDtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping()
    @Operation(summary = "유저 수정", description = "유저 본인 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)
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
    public ResponseEntity<UserResponseDto> updateUser (
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            , @RequestBody UserRequestDto userRequestDto) {

        User user = customUserDetails.getUser();

        userService.updateUser(user, userRequestDto);

        return ResponseEntity.noContent().build();
    }
}