package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class ApplicationNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationNotFoundException();

    private ApplicationNotFoundException() {
        super(ApplicationErrorCode.APPLICATION_NOT_FOUND);
    }
}
