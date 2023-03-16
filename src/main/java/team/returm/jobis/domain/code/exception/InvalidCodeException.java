package team.returm.jobis.domain.code.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidCodeException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidCodeException();

    private InvalidCodeException() {
        super(ErrorCode.INVALID_CODE);
    }
}
