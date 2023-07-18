package com.example.jobisapplication.domain.company.exception;

import com.example.jobisapplication.domain.company.exception.error.CompanyErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class CompanyNotExistsException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyNotExistsException();

    public CompanyNotExistsException() {
        super(CompanyErrorCode.COMPANY_NOT_EXISTS);
    }
}
