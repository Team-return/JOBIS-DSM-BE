package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationInProgressException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationInProgressException();

    private ApplicationInProgressException() {
        super(ApplicationErrorCode.APPLICATION_IN_PROGRESS);
    }
}
