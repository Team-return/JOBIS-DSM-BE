package com.example.jobis.global.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class ExpiredTokenException extends JobisException {
    public static final JobisException EXCEPTION = new ExpiredTokenException();
    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
