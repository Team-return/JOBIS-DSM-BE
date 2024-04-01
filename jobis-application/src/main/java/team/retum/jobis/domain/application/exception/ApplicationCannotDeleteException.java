package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class ApplicationCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationCannotDeleteException();

    private ApplicationCannotDeleteException() {
        super(ApplicationErrorCode.APPLICATION_CANNOT_DELETE);
    }
}
