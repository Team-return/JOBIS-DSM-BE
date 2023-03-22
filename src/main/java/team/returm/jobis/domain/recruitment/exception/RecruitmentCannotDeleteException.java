package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitmentCannotDeleteException extends JobisException {
    public static final JobisException EXCEPTION = new RecruitmentCannotDeleteException();

    private RecruitmentCannotDeleteException() {
        super(ErrorCode.RECRUITMENT_CANNOT_DELETE);
    }
}
