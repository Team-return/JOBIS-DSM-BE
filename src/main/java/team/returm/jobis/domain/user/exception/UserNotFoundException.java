package team.returm.jobis.domain.user.exception;

import team.returm.jobis.domain.user.exception.error.UserErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class UserNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
