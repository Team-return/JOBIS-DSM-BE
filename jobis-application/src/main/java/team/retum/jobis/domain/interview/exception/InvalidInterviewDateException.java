package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class InvalidInterviewDateException extends JobisException {

    public static final InvalidInterviewDateException EXCEPTION = new InvalidInterviewDateException();

    private InvalidInterviewDateException() {
        super(InterviewErrorCode.INVALID_INTERVIEW_DATE);
    }
}
