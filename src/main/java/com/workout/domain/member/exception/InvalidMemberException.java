package com.workout.domain.member.exception;

import com.workout.common.exception.WorkoutException;
import com.workout.common.exception.errorcode.ErrorCode;

public class InvalidMemberException extends WorkoutException {

    public InvalidMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
