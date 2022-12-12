package com.example.jobis.domain.student.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class StudentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new StudentAlreadyExistsException();

    private StudentAlreadyExistsException() {
        super(ErrorCode.STUDENT_ALREADY_EXISTS);
    }
}
