package team.retum.jobis.event.exception.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionEvent {

    private final HttpServletRequest request;
    private final Exception exception;
}
