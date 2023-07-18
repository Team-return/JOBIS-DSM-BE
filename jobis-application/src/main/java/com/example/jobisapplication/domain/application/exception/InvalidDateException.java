package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidDateException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidDateException();

    private InvalidDateException() {
        super(ApplicationErrorCode.INVALID_DATE);
    }
}
