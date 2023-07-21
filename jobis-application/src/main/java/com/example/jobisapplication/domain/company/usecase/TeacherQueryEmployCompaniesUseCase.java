package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryEmployCompaniesResponse;
import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryEmployCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public TeacherQueryEmployCompaniesResponse execute(String companyName, CompanyType type, Integer year) {
        return new TeacherQueryEmployCompaniesResponse(
                queryCompanyPort.queryEmployCompanies(companyName, type, year)
        );
    }
}
