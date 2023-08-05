package team.retum.jobis.global.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.global.error.exception.GlobalErrorCode;

public class InvalidTokenException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
