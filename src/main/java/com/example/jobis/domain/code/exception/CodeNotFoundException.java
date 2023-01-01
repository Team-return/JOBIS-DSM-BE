package com.example.jobis.domain.code.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class CodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION
            = new CodeNotFoundException();

    private CodeNotFoundException() {
        super(ErrorCode.CODE_NOT_FOUND);
    }
}
