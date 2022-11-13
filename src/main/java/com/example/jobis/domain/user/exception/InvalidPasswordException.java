package com.example.jobis.domain.user.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class InvalidPasswordException extends JobisException {

    public static final JobisException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
