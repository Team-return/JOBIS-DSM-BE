package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.company.dto.CompanyFilter;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryCompaniesResponse;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryCompaniesResponse.TeacherQueryCompanyResponse;
import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final ReviewRepository reviewRepository;
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
                .businessArea(businessArea == null ? null : queryCodePort.queryCodeById(businessArea).getKeyword())
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
