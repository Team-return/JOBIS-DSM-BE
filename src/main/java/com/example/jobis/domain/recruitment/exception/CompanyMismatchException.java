package com.example.jobis.domain.recruitment.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class CompanyMismatchException extends JobisException {
    public static final JobisException EXCEPTION =
            new CompanyMismatchException();

    private CompanyMismatchException() {
        super(ErrorCode.COMPANY_MISMATCH);
    }
}
