package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidGradeException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidGradeException();

    private InvalidGradeException() {
        super(ErrorCode.INVALID_GRADE);
    }
}
