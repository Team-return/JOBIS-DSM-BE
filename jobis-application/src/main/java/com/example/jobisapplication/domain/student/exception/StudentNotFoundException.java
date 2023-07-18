package com.example.jobisapplication.domain.student.exception;

import com.example.jobisapplication.domain.student.exception.error.StudentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class StudentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new StudentNotFoundException();

    private StudentNotFoundException() {
        super(StudentErrorCode.STUDENT_NOT_FOUND);
    }
}
