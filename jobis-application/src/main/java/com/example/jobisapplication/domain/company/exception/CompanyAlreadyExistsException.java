package com.example.jobisapplication.domain.company.exception;

import com.example.jobisapplication.domain.company.exception.error.CompanyErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class CompanyAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyAlreadyExistsException();

    private CompanyAlreadyExistsException() {
        super(CompanyErrorCode.COMPANY_ALREADY_EXISTS);
    }
}
