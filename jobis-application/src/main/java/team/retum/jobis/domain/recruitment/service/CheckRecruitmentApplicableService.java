package team.retum.jobis.domain.recruitment.service;

import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@Service
public class CheckRecruitmentApplicableService {

    public void checkRecruitmentApplicable(Company company, boolean isWinterIntern, QueryRecruitmentPort queryRecruitmentPort) {
        if (queryRecruitmentPort.existsOnRecruitmentByCompanyIdAndWinterIntern(company.getId(), isWinterIntern)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
