package team.returm.jobis.domain.file.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorProperty {

    INVALID_EXTENSION(HttpStatus.BAD_REQUEST, "Invalid Extension File"),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not Found");

    private final HttpStatus status;
    private final String message;
}
