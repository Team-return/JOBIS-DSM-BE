package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitNotFoundException();

    private RecruitNotFoundException() {
        super(ErrorCode.RECRUIT_NOT_FOUND);
    }
}
