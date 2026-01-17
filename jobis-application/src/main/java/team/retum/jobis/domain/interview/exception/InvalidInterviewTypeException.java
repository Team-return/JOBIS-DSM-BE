package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class InvalidInterviewTypeException extends JobisException {

    public static final InvalidInterviewTypeException EXCEPTION = new InvalidInterviewTypeException();

    private InvalidInterviewTypeException() {
        super(InterviewErrorCode.INVALID_INTERVIEW_TYPE);
    }
}
