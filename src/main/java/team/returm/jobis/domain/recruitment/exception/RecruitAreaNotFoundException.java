package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitAreaNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaNotFoundException();

    private RecruitAreaNotFoundException() {
        super(ErrorCode.RECRUIT_AREA_NOT_FOUND);
    }
}
