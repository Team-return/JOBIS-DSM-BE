package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class BadEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new BadEmailException();

    private BadEmailException() {
        super(GlobalErrorCode.BAD_EMAIL);
    }
}
