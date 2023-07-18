package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationNotFoundException();

    private ApplicationNotFoundException() {
        super(ApplicationErrorCode.APPLICATION_NOT_FOUND);
    }
}
