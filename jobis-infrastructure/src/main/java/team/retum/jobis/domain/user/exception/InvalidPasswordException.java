package team.retum.jobis.domain.user.exception;

import team.retum.jobis.domain.user.exception.error.UserErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidPasswordException extends JobisException {

    public static final JobisException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(UserErrorCode.INVALID_PASSWORD);
    }
}
