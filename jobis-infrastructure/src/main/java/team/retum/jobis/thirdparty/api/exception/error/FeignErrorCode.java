package team.retum.jobis.thirdparty.api.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.retum.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum FeignErrorCode implements ErrorProperty {

    FEIGN_UNAUTHORISED(HttpStatus.UNAUTHORIZED, "Feign Server Unauthorised"),
    FEIGN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Feign Server Error"),
    FEIGN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Feign Server BadRequest");

    private final HttpStatus status;
    private final String message;
}
