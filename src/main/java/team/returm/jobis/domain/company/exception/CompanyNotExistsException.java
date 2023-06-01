package team.returm.jobis.domain.company.exception;

import team.returm.jobis.domain.company.exception.error.CompanyErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CompanyNotExistsException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyNotExistsException();

    public CompanyNotExistsException() {
        super(CompanyErrorCode.COMPANY_NOT_EXISTS);
    }
}
