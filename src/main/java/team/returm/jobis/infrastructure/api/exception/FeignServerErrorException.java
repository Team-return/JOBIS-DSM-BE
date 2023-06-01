package team.returm.jobis.infrastructure.api.exception;

import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.infrastructure.api.exception.error.FeignErrorCode;

public class FeignServerErrorException extends JobisException {

    public static final JobisException EXCEPTION = new FeignServerErrorException();

    public FeignServerErrorException() {
        super(FeignErrorCode.FEIGN_SERVER_ERROR);
    }
}
