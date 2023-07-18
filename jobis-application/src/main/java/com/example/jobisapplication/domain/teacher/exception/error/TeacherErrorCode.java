package com.example.jobisapplication.domain.teacher.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum TeacherErrorCode implements ErrorProperty {

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "Teacher Not Found");

    private final HttpStatus status;
    private final String message;
}
