package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.TeacherQueryCompaniesResponse;
import team.returm.jobis.domain.company.presentation.dto.response.TeacherQueryCompaniesResponse.TeacherQueryCompanyResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public TeacherQueryCompaniesResponse execute(CompanyType type, String companyName, String region, String businessArea, Long page) {
        return new TeacherQueryCompaniesResponse(
                companyRepository.queryCompaniesByConditions(type, companyName, region, businessArea, page - 1).stream()
                        .map(company -> TeacherQueryCompanyResponse.builder()
                                .companyId(company.getCompanyId())
                                .companyName(company.getCompanyName())
                                .region(getRegionByAddress(company.getMainAddress()))
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

    private String getRegionByAddress(String address) {
        return address.substring(0, 2);
    }

}
