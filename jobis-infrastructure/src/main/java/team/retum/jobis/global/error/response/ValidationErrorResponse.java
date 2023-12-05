package team.retum.jobis.global.error.response;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class ValidationErrorResponse {
    private final String status;
    private final Map<String, String> error;

    public static ValidationErrorResponse of(BindException e) {
        Map<String, String> filedErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            filedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error(filedErrors)
                .build();
    }

    public static ValidationErrorResponse of(ConstraintViolationException e) {
        Map<String, String> filedErrors = new HashMap<>();
        for (ConstraintViolation<?> fieldError : e.getConstraintViolations()) {
            String path = fieldError.getPropertyPath().toString().split("\\.")[0];
            filedErrors.put(path, fieldError.getMessage());
        }

        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error(filedErrors)
                .build();
    }
}
