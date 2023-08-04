package team.retum.jobis.domain.company.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.company.exception.error.CompanyErrorCode;

public class CompanyAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyAlreadyExistsException();

    private CompanyAlreadyExistsException() {
        super(CompanyErrorCode.COMPANY_ALREADY_EXISTS);
    }
}
