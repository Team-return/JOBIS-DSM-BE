package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class BadAuthCodeException extends JobisException {
    public static final JobisException EXCEPTION =
            new BadAuthCodeException();

    private BadAuthCodeException() {
        super(AuthErrorCode.BAD_AUTH_CODE);
    }
}
