package com.example.jobis.domain.company.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class CompanyNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
