package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class AuthCodeNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(AuthErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
