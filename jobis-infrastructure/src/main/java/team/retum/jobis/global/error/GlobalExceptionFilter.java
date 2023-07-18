package team.retum.jobis.global.error;

import com.example.jobisapplication.common.error.ErrorProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.jobisapplication.common.error.JobisException;
import team.retum.jobis.global.error.response.ErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JobisException e) {
            ErrorProperty errorProperty = e.getErrorProperty();
            response.setStatus(errorProperty.getStatus().getValue());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ErrorResponse errorResponse =
                    new ErrorResponse(errorProperty.getStatus().getValue(), errorProperty.getMessage());

            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }
}
