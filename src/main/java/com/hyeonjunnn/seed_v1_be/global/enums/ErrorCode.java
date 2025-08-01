package com.hyeonjunnn.seed_v1_be.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //MARK: - Not Found
    DB_INFO_NOT_FOUND("E000", "DB 정보 없음", HttpStatus.NOT_FOUND),
    ADMIN_NOT_FOUND("E001", "관리자 정보 없음", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("E002", "사용자 없음", HttpStatus.NOT_FOUND),
    ANALYSIS_NOT_FOUND("E003", "분석 정보 없음", HttpStatus.NOT_FOUND),
    COMPANY_NOT_FOUND("E004", "회사 정보 없음", HttpStatus.NOT_FOUND),
    CSV_NOT_FOUND("E005", "CSV 파일 없음", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND("E006", "이미지 파일 없음", HttpStatus.NOT_FOUND),
    REQUIRE_LIST_NOT_FOUND("E007", "요청 목록(RequireList)을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND("E008", "권한(Role) 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MEMBERSHIP_NOT_FOUND("E009", "회사의 멤버십 정보가 없습니다.", HttpStatus.NOT_FOUND),
    //MARK: - Bad Request
    EMPTY_CSV("E010", "CSV 파일이 비어 있습니다.", HttpStatus.BAD_REQUEST),
    INVALID_CSV_FORMAT("E011", "CSV 파일 포맷이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    STAFF_LIMIT_EXCEEDED("E012", "직원 등록 한도를 초과했습니다.", HttpStatus.BAD_REQUEST),
    STAFF_ALREADY_EXISTS("E013", "이미 해당 직원이 추가되어 있습니다.", HttpStatus.BAD_REQUEST),
    NAME_REQUIRED("E014", "사용자 이름(name)은 필수 입력값입니다.", HttpStatus.BAD_REQUEST),
    SAME_PASSWORD("E022", "기존 비밀번호와 동일한 비밀번호는 사용할 수 없습니다.", HttpStatus.BAD_REQUEST),
    //MARK: - Server Error
    HEATMAP_READ_FAILURE("E015", "히트맵 분석 데이터 읽기 실패", HttpStatus.INTERNAL_SERVER_ERROR),
    INSIGHT_READ_FAILURE("E016", "인사이트 분석 데이터 읽기 실패", HttpStatus.INTERNAL_SERVER_ERROR),
    BEHAVIOR_PATTERN_READ_FAILURE("E017", "행동 패턴 분석 데이터 읽기 실패", HttpStatus.INTERNAL_SERVER_ERROR),
    ANALYSIS_API_CALL_FAILURE("E018", "API 분석 요청 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR),
    //MARK: - Forbidden
    ONLY_CLIENT_USER_DELETABLE("E019", "직원만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN),
    ONLY_CLIENT_ADMIN_ALLOWED("E020", "관리자 권한이 있어야 수행할 수 있습니다.", HttpStatus.FORBIDDEN),
    //MARK: - Dashboard
    DASHBOARD_API_FAILED("E030", "대시보드 데이터를 가져오는 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR),
    DASHBOARD_UNKNOWN_ERROR("E031", "대시보드 데이터를 가져오는 중 알 수 없는 예외 발생", HttpStatus.INTERNAL_SERVER_ERROR),
    //MARK: - Unauthorized
    INVALID_PASSWORD("E021", "기존 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    //MARK: -
    ACCESS_TOKEN_INVALID("E900","유효하지 않은 토큰 값입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    //MARK: - Comment / QnA 관련
    COMMENT_NOT_FOUND("E032", "해당 문의글에 대한 답변이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    DELETED_USER("E033", "탈퇴한 계정으로는 답변 작업을 할 수 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZED_ADMIN_ONLY("E034", "답변 작업은 관리자만 가능합니다.", HttpStatus.FORBIDDEN),
    POST_NOT_FOUND("E035", "문의글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    ALREADY_ANSWERED("E036", "이미 등록된 답변입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("E037", "해당 작업을 수행할 권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    INFO_COLUMN_NOT_FOUND("E038", "해당하는 InfoColumn을 찾지 못했습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}