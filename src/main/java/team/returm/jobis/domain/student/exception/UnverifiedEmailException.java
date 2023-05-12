package team.returm.jobis.domain.student.exception;

import team.returm.jobis.domain.student.exception.error.StudentErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class UnverifiedEmailException extends JobisException {
    public static final JobisException EXCEPTION =
            new UnverifiedEmailException();

    private UnverifiedEmailException() {
        super(StudentErrorCode.UNVERIFIED_EMAIL);
    }
}
