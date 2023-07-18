package team.retum.jobis.thirdparty.api.exception;

import com.example.jobisapplication.common.error.JobisException;
import team.retum.jobis.thirdparty.api.exception.error.FeignErrorCode;

public class FeignBadRequestException extends JobisException {

    public static final JobisException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException() {
        super(FeignErrorCode.FEIGN_BAD_REQUEST);
    }
}
