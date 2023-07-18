package com.example.jobisapplication.domain.auth.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.auth.exception.error.AuthErrorCode;

public class BadAuthCodeException extends JobisException {

    public static final JobisException EXCEPTION = new BadAuthCodeException();

    private BadAuthCodeException() {
        super(AuthErrorCode.BAD_AUTH_CODE);
    }
}
