package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class UnverifiedEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(ErrorCode.UNVERIFIED_EMAIL);
    }
}
