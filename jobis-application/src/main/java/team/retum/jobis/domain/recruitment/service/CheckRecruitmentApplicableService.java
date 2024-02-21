package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@Service
public class CheckRecruitmentApplicableService {
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void checkRecruitmentApplicable(Company company, boolean isWinterIntern) {
        if (queryRecruitmentPort.existsOnRecruitmentByCompanyIdAndWinterIntern(company.getId(), isWinterIntern)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
