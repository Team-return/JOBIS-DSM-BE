package team.returm.jobis.global.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ExpiredTokenException extends JobisException {
    public static final JobisException EXCEPTION = new ExpiredTokenException();
    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
