package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitmentCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentCannotDeleteException();

    private RecruitmentCannotDeleteException() {
        super(RecruitmentErrorCode.RECRUITMENT_CANNOT_DELETE);
    }
}
