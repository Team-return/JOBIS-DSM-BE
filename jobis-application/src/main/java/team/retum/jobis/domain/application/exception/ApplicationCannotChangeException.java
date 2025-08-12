package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationCannotChangeException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationCannotChangeException();

    private ApplicationCannotChangeException() {
        super(ApplicationErrorCode.APPLICATION_CANNOT_CHANGE);
    }
}

