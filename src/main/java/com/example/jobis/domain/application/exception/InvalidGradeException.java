package com.example.jobis.domain.application.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class InvalidGradeException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidGradeException();

    private InvalidGradeException() {
        super(ErrorCode.INVALID_GRADE);
    }
}
