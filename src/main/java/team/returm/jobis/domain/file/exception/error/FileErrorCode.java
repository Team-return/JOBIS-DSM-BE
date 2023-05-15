package team.returm.jobis.domain.file.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorProperty {

    INVALID_EXTENSION(400, "Invalid Extension File"),

    FILE_NOT_FOUND(404, "File not Found");

    private final int status;
    private final String message;
}
