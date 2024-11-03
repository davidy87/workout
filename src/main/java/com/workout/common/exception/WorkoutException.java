package com.workout.common.exception;

import com.workout.common.exception.errorcode.ErrorCode;

public class WorkoutException extends RuntimeException {

    public WorkoutException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
