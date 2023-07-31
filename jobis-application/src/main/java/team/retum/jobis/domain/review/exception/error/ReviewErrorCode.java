package team.retum.jobis.domain.review.exception.error;

import team.retum.jobis.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ErrorProperty {

    REVIEW_CANNOT_WRITE(HttpStatus.UNAUTHORIZED, "Review Cannot Write"),

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "Review Not Found"),

    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "Review Already Exists");

    private final HttpStatus status;
    private final String message;
}
