package team.retum.jobis.domain.file.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.file.exception.error.FileErrorCode;

public class FileNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new FileNotFoundException();

    private FileNotFoundException() {
        super(FileErrorCode.FILE_NOT_FOUND);
    }
}
