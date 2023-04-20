package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class DayOutOfRangeException extends JobisException {

    public static final JobisException EXCEPTION = new DayOutOfRangeException();

    private DayOutOfRangeException() {
        super(ErrorCode.DAY_OUT_OF_RANGE);
    }
}
