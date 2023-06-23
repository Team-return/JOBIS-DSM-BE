package team.retum.jobis.global.exception;

import team.retum.jobis.global.error.exception.GlobalErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidTokenException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
