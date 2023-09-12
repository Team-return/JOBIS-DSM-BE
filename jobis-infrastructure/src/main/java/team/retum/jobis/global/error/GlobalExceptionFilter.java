package team.retum.jobis.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.filter.OncePerRequestFilter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.event.exception.model.ExceptionEvent;
import team.retum.jobis.global.error.exception.GlobalErrorCode;
import team.retum.jobis.global.error.response.ErrorResponse;
import team.retum.jobis.global.security.auth.CurrentUserHolder;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JobisException e) {
            writeErrorResponse(response, e.getErrorProperty());
        } catch (Exception e) {
            if (e.getCause() instanceof JobisException jobisException) {
                writeErrorResponse(response, jobisException.getErrorProperty());
            } else {
                e.printStackTrace();
                eventPublisher.publishEvent(ExceptionEvent.builder()
                        .request(request)
                        .exception(e)
                        .build());
                writeErrorResponse(response, GlobalErrorCode.INTERNAL_SERVER_ERROR);
            }
        } finally {
            CurrentUserHolder.clear();
        }
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorProperty errorProperty) throws IOException {
        response.setStatus(errorProperty.getStatus().getValue());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = ErrorResponse.of(errorProperty);
        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );
    }
}
