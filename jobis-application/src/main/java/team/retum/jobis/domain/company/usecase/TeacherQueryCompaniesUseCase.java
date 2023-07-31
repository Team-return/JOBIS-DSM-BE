package team.retum.jobis.domain.company.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.TeacherQueryCompaniesResponse;
import team.retum.jobis.domain.company.dto.response.TeacherQueryCompaniesResponse.TeacherQueryCompanyResponse;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;
    private final QueryCodePort queryCodePort;

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
                .businessArea(
                        businessArea == null ? null :
                                queryCodePort.queryCodeById(businessArea)
                                        .orElseThrow(() -> CodeNotFoundException.EXCEPTION)
                                        .getKeyword()
                )
                .page(page)
                .build();

        int totalPageCount = (int) Math.ceil(
                queryCompanyPort.getTotalCompanyCount(filter).doubleValue() / 11
        );

        return new TeacherQueryCompaniesResponse(
                queryCompanyPort.queryCompaniesByConditions(filter).stream()
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
                                        queryReviewPort.queryReviewCountByCompanyId(company.getCompanyId())
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
