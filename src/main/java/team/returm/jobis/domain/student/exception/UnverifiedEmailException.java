package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class UnverifiedEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(GlobalErrorCode.UNVERIFIED_EMAIL);
    }
}
