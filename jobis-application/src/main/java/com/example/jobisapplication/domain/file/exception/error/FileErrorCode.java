package com.example.jobisapplication.domain.file.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorProperty {

    INVALID_EXTENSION(HttpStatus.BAD_REQUEST, "Invalid Extension File"),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not Found"),

    FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "File upload failed");

    private final HttpStatus status;
    private final String message;
}
