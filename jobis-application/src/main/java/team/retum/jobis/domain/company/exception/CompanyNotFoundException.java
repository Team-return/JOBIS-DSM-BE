package team.retum.jobis.domain.company.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.company.exception.error.CompanyErrorCode;

public class CompanyNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(CompanyErrorCode.COMPANY_NOT_FOUND);
    }
}
