package com.example.jobisapplication.domain.student.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum StudentErrorCode implements ErrorProperty {

    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Student Not Found"),
    CLASSROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "Classroom Not Found"),

    STUDENT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Student Already Exists");

    private final HttpStatus status;
    private final String message;
}
