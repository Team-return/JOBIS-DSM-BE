package com.example.jobis.domain.application.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class AnyApplicationStatusIsApprovedException extends JobisException {

    public static final JobisException EXCEPTION = new AnyApplicationStatusIsApprovedException();

    private AnyApplicationStatusIsApprovedException() {
        super(ErrorCode.ANY_APPLICATION_STATUS_IS_APPROVED);
    }
}
