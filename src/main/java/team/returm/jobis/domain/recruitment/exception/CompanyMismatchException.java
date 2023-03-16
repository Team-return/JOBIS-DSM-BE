package team.returm.jobis.domain.recruitment.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CompanyMismatchException extends JobisException {
    public static final JobisException EXCEPTION =
            new CompanyMismatchException();

    private CompanyMismatchException() {
        super(ErrorCode.COMPANY_MISMATCH);
    }
}
