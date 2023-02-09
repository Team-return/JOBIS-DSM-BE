package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.facade.RecruitAreaFacade;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteCompanyRecruitAreaCodeService {

    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;
    private final RecruitAreaFacade recruitAreaFacade;
    private final CodeFacade codeFacade;

    @Transactional
    public void execute(UUID recruitAreaId, Long codeId) {

        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);
        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaByIdAndRecruitment(recruitAreaId, recruitment);
        Code code = codeFacade.findCodeById(codeId);

        recruitAreaCodeRepository.deleteByRecruitAreaIdAndCodeId(recruitArea, code);
    }
}
