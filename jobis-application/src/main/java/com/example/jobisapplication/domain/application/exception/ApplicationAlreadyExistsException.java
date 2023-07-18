package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationAlreadyExistsException();

    private ApplicationAlreadyExistsException() {
        super(ApplicationErrorCode.APPLICATION_ALREADY_EXISTS);
    }
}
