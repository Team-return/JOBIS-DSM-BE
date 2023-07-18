package team.retum.jobis.domain.file.exception;

import team.retum.jobis.domain.file.exception.error.FileErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class InvalidExtensionException extends JobisException {
    public static final JobisException EXCEPTION = new InvalidExtensionException();

    private InvalidExtensionException() {
        super(FileErrorCode.INVALID_EXTENSION);
    }
}
