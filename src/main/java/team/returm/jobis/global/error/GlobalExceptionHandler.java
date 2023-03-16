package team.returm.jobis.global.error;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;
import team.returm.jobis.global.error.response.ErrorResponse;
import team.returm.jobis.global.error.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobisException.class)
    protected ResponseEntity<ErrorResponse> handleBaseException(JobisException e) {
        log.warn("RuntimeException " + e);
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ValidationErrorResponse handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> filedErrors = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            filedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error(filedErrors)
                .build();
    }
}
