package team.returm.jobis.domain.company.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CompanyAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new CompanyAlreadyExistsException();

    private CompanyAlreadyExistsException() {
        super(GlobalErrorCode.COMPANY_ALREADY_EXISTS);
    }
}
