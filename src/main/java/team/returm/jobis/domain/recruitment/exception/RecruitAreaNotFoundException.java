package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitAreaNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaNotFoundException();

    private RecruitAreaNotFoundException() {
        super(RecruitmentErrorCode.RECRUIT_AREA_NOT_FOUND);
    }
}
