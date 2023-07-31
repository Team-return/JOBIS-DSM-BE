package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;

public class AuthCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(AuthErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
