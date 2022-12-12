package com.example.jobis.domain.student.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class BadAuthCodeException extends JobisException {
    public static final JobisException EXCEPTION =
            new BadAuthCodeException();

    private BadAuthCodeException() {
        super(ErrorCode.BAD_AUTH_CODE);
    }
}
