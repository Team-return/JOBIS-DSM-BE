package team.retum.jobis.infrastructure.api.exception;

import team.retum.jobis.global.error.exception.JobisException;
import team.retum.jobis.infrastructure.api.exception.error.FeignErrorCode;

public class FeignUnauthorisedException extends JobisException {

    public static final JobisException EXCEPTION = new FeignUnauthorisedException();

    public FeignUnauthorisedException() {
        super(FeignErrorCode.FEIGN_UNAUTHORISED);
    }
}
