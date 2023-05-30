package team.returm.jobis.domain.file.exception;

import team.returm.jobis.domain.file.exception.error.FileErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class FileUploadFailedException extends JobisException {

    public static final JobisException EXCEPTION = new FileUploadFailedException();

    private FileUploadFailedException() {
        super(FileErrorCode.FILE_UPLOAD_FAILED);
    }
}
