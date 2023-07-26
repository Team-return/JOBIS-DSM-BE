package com.example.jobisapplication.domain.recruitment.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.recruitment.exception.error.RecruitmentErrorCode;

public class InvalidRecruitmentStatusException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidRecruitmentStatusException();

    private InvalidRecruitmentStatusException() {
        super(RecruitmentErrorCode.INVALID_RECRUITMENT_STATUS);
    }
}
