package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.domain.recruitment.exception.error.RecruitmentErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CompanyMismatchException extends JobisException {
    public static final JobisException EXCEPTION =
            new CompanyMismatchException();

    private CompanyMismatchException() {
        super(RecruitmentErrorCode.COMPANY_MISMATCH);
    }
}
