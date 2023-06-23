package team.retum.jobis.infrastructure.api.exception;

import team.retum.jobis.global.error.exception.JobisException;
import team.retum.jobis.infrastructure.api.exception.error.FeignErrorCode;

public class FeignBadRequestException extends JobisException {

    public static final JobisException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException() {
        super(FeignErrorCode.FEIGN_BAD_REQUEST);
    }
}
