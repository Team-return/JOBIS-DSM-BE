package team.retum.jobis.domain.auth.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.auth.exception.error.AuthErrorCode;

public class RefreshTokenNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
