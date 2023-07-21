package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobisapplication.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.user.facade.UserFacade;

@RequiredArgsConstructor
@UseCase
public class UpdateCompanyDetailsUseCase {

    private final UserFacade userFacade;

    public void execute(UpdateCompanyDetailsRequest request) {
        Company company = userFacade.getCurrentCompany();
        company.update(
                request.getAddress1(), request.getZipCode1(),
                request.getAddress2(), request.getZipCode2(),
                request.getTake(), request.getWorkerNumber(),
                request.getManager1(), request.getPhoneNumber1(),
                request.getManager2(), request.getPhoneNumber2(),
                request.getCompanyIntroduce(), request.getCompanyProfileUrl(),
                request.getFax(), request.getEmail()
        );
    }
}
