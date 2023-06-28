package team.retum.jobis.domain.company.exception;

import team.retum.jobis.domain.company.exception.error.CompanyErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class CompanyNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(CompanyErrorCode.COMPANY_NOT_FOUND);
    }
}
