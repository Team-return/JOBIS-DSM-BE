package com.example.jobis.domain.auth.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class RefreshTokenNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new RefreshTokenNotFoundException();
    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
