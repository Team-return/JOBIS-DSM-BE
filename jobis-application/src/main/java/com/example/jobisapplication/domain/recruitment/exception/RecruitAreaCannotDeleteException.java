package com.example.jobisapplication.domain.recruitment.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitAreaCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitAreaCannotDeleteException();

    private RecruitAreaCannotDeleteException() {
        super(RecruitmentErrorCode.RECRUIT_AREA_CANNOT_DELETE);
    }
}
