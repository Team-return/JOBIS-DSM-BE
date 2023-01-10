package com.example.jobis.domain.code.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class RecruitAreaCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaCodeNotFoundException();

    private RecruitAreaCodeNotFoundException() {
        super(ErrorCode.RECRUIT_AREA_CODE_NOT_FOUND);
    }
}
