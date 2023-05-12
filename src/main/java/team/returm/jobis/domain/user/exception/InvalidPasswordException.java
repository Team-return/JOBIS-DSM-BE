package team.returm.jobis.domain.user.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidPasswordException extends JobisException {

    public static final JobisException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(GlobalErrorCode.INVALID_PASSWORD);
    }
}
