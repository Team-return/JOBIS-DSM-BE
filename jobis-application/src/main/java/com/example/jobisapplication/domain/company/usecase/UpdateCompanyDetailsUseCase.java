package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.CommandCompanyPort;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateCompanyDetailsUseCase {

    private final SecurityPort securityPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;

    public void execute(UpdateCompanyDetailsRequest request) {
        Long currentUserId = securityPort.getCurrentUserId();
        Company company = queryCompanyPort.queryCompanyById(currentUserId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        commandCompanyPort.saveCompany(
                company.update(
                        request.getAddress1(), request.getZipCode1(),
                        request.getAddress2(), request.getZipCode2(),
                        request.getTake(), request.getWorkerNumber(),
                        request.getManager1(), request.getPhoneNumber1(),
                        request.getManager2(), request.getPhoneNumber2(),
                        request.getCompanyIntroduce(), request.getCompanyProfileUrl(),
                        request.getFax(), request.getEmail()
                )
        );
    }
}
