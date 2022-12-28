package com.example.jobis.domain.file.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class FileNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new FileNotFoundException();
    private FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }
}
