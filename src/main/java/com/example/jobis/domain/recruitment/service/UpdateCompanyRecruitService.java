package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UpdateCompanyRecruitService {

    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;

    @Transactional
    public void execute(UpdateRecruitmentRequest request) {

        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);

        String hiringProgress = request.getHiringProgress()
                .stream().map(Enum::toString)
                .collect(Collectors.joining(","));
        String requiredLicenses = request.getRequiredLicenses() == null?
                null : String.join(",", request.getRequiredLicenses());

        recruitment.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), requiredLicenses,
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), hiringProgress,
                request.getRequiredGrade()
        );
    }
}
