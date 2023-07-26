package com.example.jobisapplication.domain.recruitment.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitmentCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentCannotDeleteException();

    private RecruitmentCannotDeleteException() {
        super(RecruitmentErrorCode.RECRUITMENT_CANNOT_DELETE);
    }
}
