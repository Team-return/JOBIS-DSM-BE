package team.retum.jobis.domain.application.exception;

import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class ApplicationAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationAlreadyExistsException();

    private ApplicationAlreadyExistsException() {
        super(ApplicationErrorCode.APPLICATION_ALREADY_EXISTS);
    }
}
