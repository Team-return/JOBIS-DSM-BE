package com.example.jobisapplication.domain.file.exception;

import com.example.jobisapplication.domain.file.exception.error.FileErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class FileUploadFailedException extends JobisException {

    public static final JobisException EXCEPTION = new FileUploadFailedException();

    private FileUploadFailedException() {
        super(FileErrorCode.FILE_UPLOAD_FAILED);
    }
}
