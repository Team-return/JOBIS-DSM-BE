package team.returm.jobis.domain.application.exception;

import team.returm.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ApplicationNotFoundException();

    private ApplicationNotFoundException() {
        super(ApplicationErrorCode.APPLICATION_NOT_FOUND);
    }
}
