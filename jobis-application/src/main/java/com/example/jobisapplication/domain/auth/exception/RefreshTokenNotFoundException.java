package com.example.jobisapplication.domain.auth.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.auth.exception.error.AuthErrorCode;

public class RefreshTokenNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
