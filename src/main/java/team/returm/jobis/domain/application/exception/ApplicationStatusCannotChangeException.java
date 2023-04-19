package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationStatusCannotChangeException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(ErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
