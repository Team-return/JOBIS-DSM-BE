package team.returm.jobis.domain.code.exception;

import team.returm.jobis.domain.code.exception.error.CodeErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class RecruitAreaCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaCodeNotFoundException();

    private RecruitAreaCodeNotFoundException() {
        super(CodeErrorCode.RECRUIT_AREA_CODE_NOT_FOUND);
    }
}
