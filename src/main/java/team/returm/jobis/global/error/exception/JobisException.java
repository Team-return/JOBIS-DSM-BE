package team.returm.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public class JobisException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
