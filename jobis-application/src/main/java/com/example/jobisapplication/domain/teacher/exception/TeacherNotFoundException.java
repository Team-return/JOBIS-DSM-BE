package com.example.jobisapplication.domain.teacher.exception;

import com.example.jobisapplication.domain.teacher.exception.error.TeacherErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class TeacherNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(TeacherErrorCode.TEACHER_NOT_FOUND);
    }
}
