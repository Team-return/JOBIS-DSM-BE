package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateCompanyDetailsService {

    private final CompanyRepository companyRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateCompanyDetailsRequest request) {
        UUID currentUserId = userFacade.getCurrentUserId();
        Company company = companyRepository.queryCompanyById(currentUserId)
                        .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

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
