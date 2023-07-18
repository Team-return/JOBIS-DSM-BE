package com.example.jobisapplication.domain.recruitment.exception;

import com.example.jobisapplication.domain.recruitment.exception.error.RecruitmentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class CompanyMismatchException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyMismatchException();

    private CompanyMismatchException() {
        super(RecruitmentErrorCode.COMPANY_MISMATCH);
    }
}
