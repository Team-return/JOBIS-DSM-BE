package team.retum.jobis.event.exception.model;

import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;

@Getter
@Builder
public class ExceptionEvent {

    private final HttpServletRequest request;
    private final Exception e;
}
