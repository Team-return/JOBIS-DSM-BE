package team.returm.jobis.domain.auth.exception;

import team.returm.jobis.domain.auth.exception.error.AuthErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class AuthCodeNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(AuthErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
