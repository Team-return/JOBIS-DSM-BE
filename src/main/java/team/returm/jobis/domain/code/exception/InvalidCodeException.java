package team.returm.jobis.domain.code.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidCodeException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidCodeException();

    private InvalidCodeException() {
        super(GlobalErrorCode.INVALID_CODE);
    }
}
