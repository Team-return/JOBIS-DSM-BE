package team.returm.jobis.domain.auth.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class AuthCodeNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new AuthCodeNotFoundException();

    private AuthCodeNotFoundException() {
        super(GlobalErrorCode.AUTH_CODE_NOT_FOUND);
    }
}
