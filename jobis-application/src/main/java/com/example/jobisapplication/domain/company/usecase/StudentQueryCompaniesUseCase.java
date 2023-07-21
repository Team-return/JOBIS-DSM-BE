package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.company.dto.CompanyFilter;
import com.example.jobisapplication.domain.company.dto.response.StudentQueryCompaniesResponse;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public StudentQueryCompaniesResponse execute(Long page, String name) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(name)
                .page(page)
                .build();

        return new StudentQueryCompaniesResponse(
                queryCompanyPort.queryCompanyVoList(filter)
        );
    }
}
