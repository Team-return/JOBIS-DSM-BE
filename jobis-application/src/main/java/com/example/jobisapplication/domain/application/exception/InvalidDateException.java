package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class InvalidDateException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidDateException();

    private InvalidDateException() {
        super(ApplicationErrorCode.INVALID_DATE);
    }
}
