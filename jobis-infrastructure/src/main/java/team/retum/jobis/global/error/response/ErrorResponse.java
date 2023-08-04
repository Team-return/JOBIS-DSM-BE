package team.retum.jobis.global.error.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final Integer status;
    private final String message;

    public static ErrorResponse of(ErrorProperty errorProperty) {
        return new ErrorResponse(errorProperty.getStatus().getValue(), errorProperty.getMessage());
    }
}
