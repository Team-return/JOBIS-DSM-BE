package team.returm.jobis.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.global.error.response.ErrorResponse;
import team.returm.jobis.global.error.response.ValidationErrorResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobisException.class)
    protected ResponseEntity<ErrorResponse> handleBaseException(JobisException e) {
        log.warn("RuntimeException " + e);
        ErrorProperty errorProperty = e.getErrorProperty();
        ErrorResponse response = ErrorResponse.builder()
                .message(errorProperty.getMessage())
                .status(errorProperty.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorProperty.getStatus()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ValidationErrorResponse handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> filedErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            filedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error(filedErrors)
                .build();
    }
}
