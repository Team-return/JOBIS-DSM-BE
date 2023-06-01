package team.returm.jobis.infrastructure.feignClients.exception;

import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.infrastructure.feignClients.exception.error.FeignErrorCode;

public class FeignServerErrorException extends JobisException {

    public static final JobisException EXCEPTION = new FeignServerErrorException();

    public FeignServerErrorException() {
        super(FeignErrorCode.FEIGN_SERVER_ERROR);
    }
}
