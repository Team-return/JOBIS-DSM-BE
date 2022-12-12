package com.example.jobis.domain.student.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class BadEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new BadEmailException();

    private BadEmailException() {
        super(ErrorCode.BAD_EMAIL);
    }
}
