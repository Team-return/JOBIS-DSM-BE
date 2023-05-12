package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidStudentException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidStudentException();

    private InvalidStudentException() {
        super(GlobalErrorCode.INVALID_STUDENT);
    }
}
