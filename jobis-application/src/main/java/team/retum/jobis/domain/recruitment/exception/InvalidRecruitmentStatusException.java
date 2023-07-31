package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;

public class InvalidRecruitmentStatusException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidRecruitmentStatusException();

    private InvalidRecruitmentStatusException() {
        super(RecruitmentErrorCode.INVALID_RECRUITMENT_STATUS);
    }
}
