package team.returm.jobis.domain.application.exception;

import team.returm.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationCannotDeleteException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationCannotDeleteException();

    private ApplicationCannotDeleteException() {
        super(ApplicationErrorCode.APPLICATION_CANNOT_DELETE);
    }
}
