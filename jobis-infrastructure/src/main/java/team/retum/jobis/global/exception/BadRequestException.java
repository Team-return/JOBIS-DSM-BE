package team.retum.jobis.global.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.global.error.exception.GlobalErrorCode;

public class BadRequestException extends JobisException {

    public static final JobisException EXCEPTION = new BadRequestException();

    public BadRequestException() {
        super(GlobalErrorCode.BAD_REQUEST);
    }
}
