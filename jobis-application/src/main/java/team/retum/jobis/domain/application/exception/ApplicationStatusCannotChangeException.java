package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationStatusCannotChangeException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(ApplicationErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
