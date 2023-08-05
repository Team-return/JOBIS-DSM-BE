package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;

public class BadAuthCodeException extends JobisException {

    public static final JobisException EXCEPTION = new BadAuthCodeException();

    private BadAuthCodeException() {
        super(AuthErrorCode.BAD_AUTH_CODE);
    }
}
