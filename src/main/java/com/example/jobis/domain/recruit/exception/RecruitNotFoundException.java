package com.example.jobis.domain.recruit.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class RecruitNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitNotFoundException();

    private RecruitNotFoundException() {
        super(ErrorCode.RECRUIT_NOT_FOUND);
    }
}
