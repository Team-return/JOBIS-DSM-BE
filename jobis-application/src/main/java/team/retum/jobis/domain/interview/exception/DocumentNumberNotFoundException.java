package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class DocumentNumberNotFoundException extends JobisException {

    public static final DocumentNumberNotFoundException EXCEPTION = new DocumentNumberNotFoundException();

    private DocumentNumberNotFoundException() {
        super(InterviewErrorCode.DOCUMENT_NUMBER_NOT_FOUND);
    }
}
