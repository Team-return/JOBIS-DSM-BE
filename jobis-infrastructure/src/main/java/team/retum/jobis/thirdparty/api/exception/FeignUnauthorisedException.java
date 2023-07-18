package team.retum.jobis.thirdparty.api.exception;

import com.example.jobisapplication.common.error.JobisException;
import team.retum.jobis.thirdparty.api.exception.error.FeignErrorCode;

public class FeignUnauthorisedException extends JobisException {

    public static final JobisException EXCEPTION = new FeignUnauthorisedException();

    public FeignUnauthorisedException() {
        super(FeignErrorCode.FEIGN_UNAUTHORISED);
    }
}
