package team.retum.jobis.domain.file.exception;

import team.retum.jobis.domain.file.exception.error.FileErrorCode;
import team.retum.jobis.common.error.JobisException;

public class FileUploadFailedException extends JobisException {

    public static final JobisException EXCEPTION = new FileUploadFailedException();

    private FileUploadFailedException() {
        super(FileErrorCode.FILE_UPLOAD_FAILED);
    }
}
