package team.returm.jobis.domain.auth.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorProperty {

    UNVERIFIED_EMAIL(401, "Unverified Email"),
    BAD_AUTH_CODE(401, "Bad Auth Code"),

    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),
    AUTH_CODE_NOT_FOUND(404, "AuthCode Not Found");

    private final Integer status;
    private final String message;
}
