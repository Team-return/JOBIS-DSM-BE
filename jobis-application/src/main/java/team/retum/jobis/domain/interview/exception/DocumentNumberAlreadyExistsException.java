package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class DocumentNumberAlreadyExistsException extends JobisException {

    public static final DocumentNumberAlreadyExistsException EXCEPTION = new DocumentNumberAlreadyExistsException();

    private DocumentNumberAlreadyExistsException() {
        super(InterviewErrorCode.DOCUMENT_NUMBER_ALREADY_EXISTS);
    }
}
