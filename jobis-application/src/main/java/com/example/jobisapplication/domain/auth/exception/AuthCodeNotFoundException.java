package com.example.jobisapplication.domain.auth.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.auth.exception.error.AuthErrorCode;

public class AuthCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(AuthErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
