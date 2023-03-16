package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationAlreadyExistsException();

    private ApplicationAlreadyExistsException() {
        super(ErrorCode.APPLICATION_ALREADY_EXISTS);
    }
}
