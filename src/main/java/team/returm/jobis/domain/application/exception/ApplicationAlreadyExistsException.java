package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationAlreadyExistsException();

    private ApplicationAlreadyExistsException() {
        super(GlobalErrorCode.APPLICATION_ALREADY_EXISTS);
    }
}
