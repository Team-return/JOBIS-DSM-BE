package team.retum.jobis.domain.user.exception;

import team.retum.jobis.domain.user.exception.error.UserErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class UserNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
