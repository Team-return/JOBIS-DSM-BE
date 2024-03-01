package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CompanyQueryApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public CompanyQueryApplicationsResponse execute() {
        Company company = securityPort.getCurrentCompany();

        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(company.getId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        ApplicationFilter applicationFilter = ApplicationFilter.builder()
                .recruitmentId(recruitment.getId())
                .applicationStatus(ApplicationStatus.SEND)
                .build();

        List<ApplicationVO> applicationVOs = queryApplicationPort.queryApplicationByConditions(applicationFilter);

        return CompanyQueryApplicationsResponse.of(applicationVOs);
    }
}
