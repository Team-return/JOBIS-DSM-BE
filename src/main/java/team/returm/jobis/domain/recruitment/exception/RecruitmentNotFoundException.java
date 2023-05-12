package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitmentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new RecruitmentNotFoundException();

    private RecruitmentNotFoundException() {
        super(GlobalErrorCode.RECRUIT_NOT_FOUND);
    }
}
