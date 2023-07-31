package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitAreaCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitAreaCannotDeleteException();

    private RecruitAreaCannotDeleteException() {
        super(RecruitmentErrorCode.RECRUIT_AREA_CANNOT_DELETE);
    }
}
