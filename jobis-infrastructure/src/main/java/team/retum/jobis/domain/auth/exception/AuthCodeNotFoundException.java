package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class AuthCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(AuthErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
