package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;

public class UnverifiedEmailException extends JobisException {

    public static final JobisException EXCEPTION = new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(AuthErrorCode.UNVERIFIED_EMAIL);
    }
}
