package team.returm.jobis.domain.file.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class FileNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new FileNotFoundException();

    private FileNotFoundException() {
        super(GlobalErrorCode.FILE_NOT_FOUND);
    }
}
