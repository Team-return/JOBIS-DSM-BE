package com.example.jobisapplication.domain.auth.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.auth.exception.error.AuthErrorCode;

public class UnverifiedEmailException extends JobisException {

    public static final JobisException EXCEPTION = new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(AuthErrorCode.UNVERIFIED_EMAIL);
    }
}
