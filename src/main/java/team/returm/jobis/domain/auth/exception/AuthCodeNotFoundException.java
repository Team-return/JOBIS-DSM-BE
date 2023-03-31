package team.returm.jobis.domain.auth.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class AuthCodeNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(ErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
