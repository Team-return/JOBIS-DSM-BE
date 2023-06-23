package team.retum.jobis.infrastructure.api.exception;

import team.retum.jobis.global.error.exception.JobisException;
import team.retum.jobis.infrastructure.api.exception.error.FeignErrorCode;

public class FeignServerErrorException extends JobisException {

    public static final JobisException EXCEPTION = new FeignServerErrorException();

    public FeignServerErrorException() {
        super(FeignErrorCode.FEIGN_SERVER_ERROR);
    }
}
