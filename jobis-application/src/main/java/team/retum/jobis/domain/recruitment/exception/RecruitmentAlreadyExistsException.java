package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;

public class RecruitmentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentAlreadyExistsException();

    private RecruitmentAlreadyExistsException() {
        super(RecruitmentErrorCode.RECRUITMENT_ALREADY_EXISTS);
    }
}
