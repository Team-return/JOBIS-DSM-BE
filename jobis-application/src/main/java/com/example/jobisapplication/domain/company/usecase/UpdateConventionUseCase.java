package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.company.dto.request.UpdateMouRequest;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.CommandCompanyPort;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class UpdateConventionUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    public void execute(UpdateMouRequest request) {
        List<Company> companies = queryCompanyPort.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        commandCompanyPort.saveAllCompanies(
                companies.stream()
                        .map(Company::convertToMou)
                        .toList()
        );
    }
}
