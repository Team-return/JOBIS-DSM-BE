package com.example.jobis.domain.company.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class CompanyAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new CompanyAlreadyExistsException();

    private CompanyAlreadyExistsException() {
        super(ErrorCode.COMPANY_ALREADY_EXISTS);
    }
}
