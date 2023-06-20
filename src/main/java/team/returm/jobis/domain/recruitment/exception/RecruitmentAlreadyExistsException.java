package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitmentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentAlreadyExistsException();

    private RecruitmentAlreadyExistsException() {
        super(RecruitmentErrorCode.RECRUITMENT_ALREADY_EXISTS);
    }
}
