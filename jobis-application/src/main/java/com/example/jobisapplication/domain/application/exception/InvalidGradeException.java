package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidGradeException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidGradeException();

    private InvalidGradeException() {
        super(ApplicationErrorCode.INVALID_GRADE);
    }
}
