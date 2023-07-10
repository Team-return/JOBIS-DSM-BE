package team.retum.jobis.domain.bug.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.retum.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BugErrorCode implements ErrorProperty {

    BUG_REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "Bug Report Not Found");

    private final HttpStatus status;
    private final String message;
}
