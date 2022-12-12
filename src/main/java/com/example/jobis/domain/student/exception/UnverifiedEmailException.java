package com.example.jobis.domain.student.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class UnverifiedEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(ErrorCode.UNVERIFIED_EMAIL);
    }
}
