package team.retum.jobis.domain.bug.exception.error;

import team.retum.jobis.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BugErrorCode implements ErrorProperty {

    BUG_REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "Bug Report Not Found");

    private final HttpStatus status;
    private final String message;
}
