package team.returm.jobis.domain.code.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitAreaCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaCodeNotFoundException();

    private RecruitAreaCodeNotFoundException() {
        super(GlobalErrorCode.RECRUIT_AREA_CODE_NOT_FOUND);
    }
}
