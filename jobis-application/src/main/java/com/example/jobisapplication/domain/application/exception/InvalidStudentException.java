package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidStudentException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidStudentException();

    private InvalidStudentException() {
        super(ApplicationErrorCode.INVALID_STUDENT);
    }
}
