package team.retum.jobis.thirdparty.api.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum FeignErrorCode implements ErrorProperty {

    FEIGN_UNAUTHORISED(HttpStatus.UNAUTHORIZED, "Feign Server Unauthorised"),
    FEIGN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Feign Server Error"),
    FEIGN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Feign Server BadRequest");

    private final HttpStatus status;
    private final String message;
}
