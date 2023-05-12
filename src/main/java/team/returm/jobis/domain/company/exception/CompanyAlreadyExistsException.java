package team.returm.jobis.domain.company.exception;

import team.returm.jobis.domain.company.exception.error.CompanyErrorCode;
import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CompanyAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new CompanyAlreadyExistsException();

    private CompanyAlreadyExistsException() {
        super(CompanyErrorCode.COMPANY_ALREADY_EXISTS);
    }
}
