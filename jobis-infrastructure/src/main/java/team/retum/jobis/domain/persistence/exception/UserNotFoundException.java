package team.retum.jobis.domain.persistence.exception;

import team.retum.jobis.domain.persistence.exception.error.UserErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class UserNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
