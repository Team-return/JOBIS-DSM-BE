package team.returm.jobis.infrastructure.api.exception;

import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.infrastructure.api.exception.error.FeignErrorCode;

public class FeignUnauthorisedException extends JobisException {

    public static final JobisException EXCEPTION = new FeignUnauthorisedException();

    public FeignUnauthorisedException() {
        super(FeignErrorCode.FEIGN_UNAUTHORISED);
    }
}
