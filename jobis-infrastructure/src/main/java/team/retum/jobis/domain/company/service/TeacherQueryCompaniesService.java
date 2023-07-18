package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.facade.CodeFacade;
import com.example.jobisapplication.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import com.example.jobisapplication.domain.company.dto.CompanyFilter;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryCompaniesResponse;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryCompaniesResponse.TeacherQueryCompanyResponse;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryCompaniesService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final CodeFacade codeFacade;

    public TeacherQueryCompaniesResponse execute(
            CompanyType type,
            String companyName,
            String region,
            Long businessArea,
            Long page
    ) {
        CompanyFilter filter = CompanyFilter.builder()
                .type(type)
                .name(companyName)
                .region(region)
                .businessArea(businessArea == null ? null : codeFacade.findCodeById(businessArea).getKeyword())
                .page(page)
                .build();

        int totalPageCount = (int) Math.ceil(
                companyRepository.getTotalCompanyCount(filter).doubleValue() / 11
        );

        return new TeacherQueryCompaniesResponse(
                companyRepository.queryCompaniesByConditions(filter).stream()
                        .map(company -> TeacherQueryCompanyResponse.builder()
                                .companyId(company.getCompanyId())
                                .companyName(company.getCompanyName())
                                .region(getRegionByAddress(company.getMainAddress()))
                                .businessArea(company.getBusinessArea())
                                .workersCount(company.getWorkersCount())
                                .take(company.getTake())
                                .companyType(company.getCompanyType())
                                .convention(company.getConvention())
                                .personalContact(company.getPersonalContact())
                                .recentRecruitYear(company.getRecentRecruitYear())
                                .totalAcceptanceCount(company.getTotalAcceptanceCount())
                                .reviewCount(
                                        reviewRepository.countByCompanyId(company.getCompanyId())
                                )
                                .build()
                        ).toList(),
                totalPageCount
        );
    }

    private String getRegionByAddress(String address) {
        return address.substring(0, 2);
    }

}
