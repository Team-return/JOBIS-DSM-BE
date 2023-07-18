package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class RecruitAreaNotFoundException extends JobisException {

    public static final JobisException EXCEPTION =
            new RecruitAreaNotFoundException();

    private RecruitAreaNotFoundException() {
        super(RecruitmentErrorCode.RECRUIT_AREA_NOT_FOUND);
    }
}
