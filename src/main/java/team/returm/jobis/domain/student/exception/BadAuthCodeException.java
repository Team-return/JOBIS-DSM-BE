package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class BadAuthCodeException extends JobisException {
    public static final JobisException EXCEPTION =
            new BadAuthCodeException();

    private BadAuthCodeException() {
        super(ErrorCode.BAD_AUTH_CODE);
    }
}
