package team.retum.jobis.domain.user.exception;

import team.retum.jobis.domain.user.exception.error.UserErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class InvalidPasswordException extends JobisException {

    public static final JobisException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(UserErrorCode.INVALID_PASSWORD);
    }
}
