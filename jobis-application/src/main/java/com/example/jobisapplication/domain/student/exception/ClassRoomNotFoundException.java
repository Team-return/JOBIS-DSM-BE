package com.example.jobisapplication.domain.student.exception;

import com.example.jobisapplication.domain.student.exception.error.StudentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class ClassRoomNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ClassRoomNotFoundException();

    private ClassRoomNotFoundException() {
        super(StudentErrorCode.CLASSROOM_NOT_FOUND);
    }
}
