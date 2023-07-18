package com.example.jobisapplication.domain.file.exception;

import com.example.jobisapplication.domain.file.exception.error.FileErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class FileNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new FileNotFoundException();

    private FileNotFoundException() {
        super(FileErrorCode.FILE_NOT_FOUND);
    }
}
