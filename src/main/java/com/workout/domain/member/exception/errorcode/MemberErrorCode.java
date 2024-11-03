package com.workout.domain.member.exception.errorcode;

import com.workout.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_USERNAME", "이미 사용 중인 닉네임입니다."),
    INCORRECT_EMAIL(HttpStatus.BAD_REQUEST.value(), "INVALID_EMAIL", "올바르지 않은 이메일입니다."),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST.value(), "INVALID_PASSWORD", "올바르지 않은 비빌번호입니다."),
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "MEMBER_NOT_EXIST", "존재하지 않는 회원입니다."),
    INVALID_NEW_USERNAME(HttpStatus.BAD_REQUEST.value(), "INVALID_NEW_USERNAME", "동일한 닉네임으로는 변경할 수 없습니다.");

    private final int status;

    private final String code;

    private final String message;
}
