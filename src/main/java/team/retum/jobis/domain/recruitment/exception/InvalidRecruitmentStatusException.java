package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class InvalidRecruitmentStatusException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidRecruitmentStatusException();
    private InvalidRecruitmentStatusException() {
        super(RecruitmentErrorCode.INVALID_RECRUITMENT_STATUS);
    }
}
