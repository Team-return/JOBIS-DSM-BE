package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitAreaFacade;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateCompanyRecruitAreaService {

    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;
    private final RecruitAreaFacade recruitAreaFacade;

    @Transactional
    public void execute(UpdateRecruitAreaRequest request, UUID recruitAreaId) {

        Company company = companyFacade.getCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaByIdAndRecruitment(recruitAreaId, recruitment);
        recruitArea.update(request.getHiring(), request.getMajorTask());
    }
}
