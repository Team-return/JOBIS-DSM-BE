package team.retum.jobis.domain.file.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.file.exception.error.FileErrorCode;

public class InvalidExtensionException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidExtensionException();

    private InvalidExtensionException() {
        super(FileErrorCode.INVALID_EXTENSION);
    }
}
