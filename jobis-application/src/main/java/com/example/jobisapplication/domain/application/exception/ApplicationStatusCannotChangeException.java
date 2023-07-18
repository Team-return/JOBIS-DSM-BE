package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationStatusCannotChangeException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(ApplicationErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
