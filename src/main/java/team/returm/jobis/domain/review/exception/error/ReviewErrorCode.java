package team.returm.jobis.domain.review.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ErrorProperty {

    REVIEW_CANNOT_WRITE(401, "Review Cannot Write"),

    REVIEW_NOT_FOUND(404, "Review Not Found");

    private final int status;
    private final String message;
}
