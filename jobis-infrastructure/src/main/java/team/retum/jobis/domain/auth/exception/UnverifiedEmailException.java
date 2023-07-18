package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class UnverifiedEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(AuthErrorCode.UNVERIFIED_EMAIL);
    }
}
