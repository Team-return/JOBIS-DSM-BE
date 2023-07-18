package team.retum.jobis.domain.recruitment.exception;

import team.retum.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class RecruitmentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new RecruitmentAlreadyExistsException();

    private RecruitmentAlreadyExistsException() {
        super(RecruitmentErrorCode.RECRUITMENT_ALREADY_EXISTS);
    }
}
