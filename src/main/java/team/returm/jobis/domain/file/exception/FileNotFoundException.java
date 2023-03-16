package team.returm.jobis.domain.file.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class FileNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new FileNotFoundException();
    private FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }
}
