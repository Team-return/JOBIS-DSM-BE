package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidDateFilterRangeException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidDateFilterRangeException();

    private InvalidDateFilterRangeException() {
        super(GlobalErrorCode.INVALID_DATE_FILTER_RANGE);
    }
}
