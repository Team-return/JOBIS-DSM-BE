package team.retum.jobis.domain.code.exception;

import team.retum.jobis.domain.code.exception.error.CodeErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class RecruitAreaCodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaCodeNotFoundException();

    private RecruitAreaCodeNotFoundException() {
        super(CodeErrorCode.RECRUIT_AREA_CODE_NOT_FOUND);
    }
}
