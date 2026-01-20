package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class InterviewNotFoundException extends JobisException {

    public static final InterviewNotFoundException EXCEPTION = new InterviewNotFoundException();

    private InterviewNotFoundException() {
        super(InterviewErrorCode.INTERVIEW_NOT_FOUND);
    }
}
