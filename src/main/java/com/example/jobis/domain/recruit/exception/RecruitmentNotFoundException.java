package com.example.jobis.domain.recruit.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class RecruitmentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new RecruitmentNotFoundException();

    private RecruitmentNotFoundException() {
        super(ErrorCode.RECRUIT_NOT_FOUND);
    }
}
