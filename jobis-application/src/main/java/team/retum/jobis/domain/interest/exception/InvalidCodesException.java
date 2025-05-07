package team.retum.jobis.domain.interest.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interest.exception.error.InterestErrorCode;

public class InvalidCodesException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidCodesException();

    private InvalidCodesException() {
        super(InterestErrorCode.INVALID_CODES);
    }
}
