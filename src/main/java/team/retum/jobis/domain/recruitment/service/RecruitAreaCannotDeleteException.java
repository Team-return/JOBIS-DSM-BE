package team.retum.jobis.domain.recruitment.service;

import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class RecruitAreaCannotDeleteException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitAreaCannotDeleteException();

    private RecruitAreaCannotDeleteException() {
        super(RecruitmentErrorCode.RECRUIT_AREA_CANNOT_DELETE);
    }
}
