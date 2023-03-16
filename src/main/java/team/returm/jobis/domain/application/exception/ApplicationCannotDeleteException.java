package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationCannotDeleteException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationCannotDeleteException();

    private ApplicationCannotDeleteException() {
        super(ErrorCode.APPLICATION_CANNOT_DELETE);
    }
}
