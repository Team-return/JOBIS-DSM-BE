package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationStatusCannotChangeException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(GlobalErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
