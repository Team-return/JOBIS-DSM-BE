package com.example.jobis.domain.application.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class NotThirdGradeException extends JobisException {

    public static final JobisException EXCEPTION = new NotThirdGradeException();

    private NotThirdGradeException() {
        super(ErrorCode.NOT_THIRD_GRADE);
    }
}
