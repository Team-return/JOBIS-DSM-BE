package team.returm.jobis.domain.file.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidExtensionException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidExtensionException();

    private InvalidExtensionException() {
        super(GlobalErrorCode.INVALID_EXTENSION);
    }
}
