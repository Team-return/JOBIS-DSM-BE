package team.retum.jobis.domain.company.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.company.exception.error.CompanyErrorCode;

public class CompanyNotExistsException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyNotExistsException();

    public CompanyNotExistsException() {
        super(CompanyErrorCode.COMPANY_NOT_EXISTS);
    }
}
