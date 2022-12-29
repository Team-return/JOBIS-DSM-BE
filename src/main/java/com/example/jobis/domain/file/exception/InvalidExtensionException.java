package com.example.jobis.domain.file.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class InvalidExtensionException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidExtensionException();
    private InvalidExtensionException() {
        super(ErrorCode.INVALID_EXTENSION);
    }
}
