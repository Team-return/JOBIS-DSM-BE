package team.returm.jobis.infrastructure.feignClients.exception;

import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.infrastructure.feignClients.exception.error.FeignErrorCode;

public class FeignBadRequestException extends JobisException {

    public static final JobisException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException() {
        super(FeignErrorCode.FEIGN_BAD_REQUEST);
    }
}
