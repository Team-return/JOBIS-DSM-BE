package com.example.jobisapplication.domain.code.exception;

import com.example.jobisapplication.domain.code.exception.error.CodeErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class CodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new CodeNotFoundException();

    private CodeNotFoundException() {
        super(CodeErrorCode.CODE_NOT_FOUND);
    }
}
