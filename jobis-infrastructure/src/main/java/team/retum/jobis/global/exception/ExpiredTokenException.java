package team.retum.jobis.global.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.global.error.exception.GlobalErrorCode;

public class ExpiredTokenException extends JobisException {

    public static final JobisException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalErrorCode.EXPIRED_TOKEN);
    }
}
