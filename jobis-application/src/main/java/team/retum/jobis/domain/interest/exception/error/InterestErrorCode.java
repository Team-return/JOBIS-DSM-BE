package team.retum.jobis.domain.interest.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum InterestErrorCode implements ErrorProperty {

    INTEREST_NOT_FOUND(HttpStatus.NOT_FOUND, "Interest Not Found");

    private final HttpStatus status;
    private final String message;
}
