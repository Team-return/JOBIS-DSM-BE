package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.TeacherQueryCompaniesResponse;
import team.returm.jobis.domain.company.presentation.dto.response.TeacherQueryCompaniesResponse.TeacherQueryCompanyResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.util.StringUtil;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public TeacherQueryCompaniesResponse execute(Long page) {
        return new TeacherQueryCompaniesResponse(
                companyRepository.queryCompaniesByConditions(page - 1).stream()
                        .map(company -> TeacherQueryCompanyResponse.builder()
                                .companyId(company.getCompanyId())
                                .companyName(company.getCompanyName())
                                .region(StringUtil.getRegionByAddress(company.getMainAddress()))
                                .businessArea(company.getBusinessArea())
                                .workersCount(company.getWorkersCount())
                                .sales(company.getSales())
                                .companyType(company.getCompanyType())
                                .recentRecruitYear(company.getRecentRecruitYear())
                                .totalAcceptanceCount(company.getTotalAcceptanceCount())
                                //TODO :: 후기 개수 반환
                                .build()
                        ).toList()
        );
    }
}
