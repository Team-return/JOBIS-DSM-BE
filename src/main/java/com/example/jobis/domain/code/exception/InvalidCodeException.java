package com.example.jobis.domain.code.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class InvalidCodeException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidCodeException();

    private InvalidCodeException() {
        super(ErrorCode.INVALID_CODE);
    }
}
