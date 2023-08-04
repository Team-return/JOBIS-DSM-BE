package team.retum.jobis.domain.user.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.user.exception.error.UserErrorCode;

public class UserNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
