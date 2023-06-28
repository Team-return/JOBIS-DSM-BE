package team.retum.jobis.domain.file.exception;

import team.retum.jobis.domain.file.exception.error.FileErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidExtensionException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidExtensionException();

    private InvalidExtensionException() {
        super(FileErrorCode.INVALID_EXTENSION);
    }
}
