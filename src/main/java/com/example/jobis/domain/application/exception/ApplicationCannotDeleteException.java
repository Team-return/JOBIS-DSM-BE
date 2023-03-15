package com.example.jobis.domain.application.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class ApplicationCannotDeleteException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationCannotDeleteException();

    private ApplicationCannotDeleteException() {
        super(ErrorCode.APPLICATION_CANNOT_DELETE);
    }
}
