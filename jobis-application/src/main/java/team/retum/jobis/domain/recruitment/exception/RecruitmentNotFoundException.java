package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitmentNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentNotFoundException();

    private RecruitmentNotFoundException() {
        super(RecruitmentErrorCode.RECRUITMENT_NOT_FOUND);
    }
}
