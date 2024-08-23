package team.retum.jobis.domain.interest.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interest.exception.error.InterestErrorCode;

public class InterestNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new InterestNotFoundException();

    private InterestNotFoundException() {
        super(InterestErrorCode.INTEREST_NOT_FOUND);
    }
}
