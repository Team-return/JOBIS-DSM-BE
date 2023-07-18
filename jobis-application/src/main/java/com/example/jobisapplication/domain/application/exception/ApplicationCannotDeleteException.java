package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationCannotDeleteException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationCannotDeleteException();

    private ApplicationCannotDeleteException() {
        super(ApplicationErrorCode.APPLICATION_CANNOT_DELETE);
    }
}
