package team.returm.jobis.domain.code.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum CodeErrorCode implements ErrorProperty {

    INVALID_CODE(401, "invalid code"),

    RECRUIT_AREA_CODE_NOT_FOUND(404, "Recruit Area Code Not Found"),
    CODE_NOT_FOUND(404, "Code Not Found");

    private final Integer status;
    private final String message;
}
