package team.retum.jobis.thirdparty.api.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.thirdparty.api.exception.error.FeignErrorCode;

public class FeignServerErrorException extends JobisException {

    public static final JobisException EXCEPTION = new FeignServerErrorException();

    public FeignServerErrorException() {
        super(FeignErrorCode.FEIGN_SERVER_ERROR);
    }
}
