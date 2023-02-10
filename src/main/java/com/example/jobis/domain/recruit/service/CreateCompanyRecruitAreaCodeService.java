package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaCodeRequest;
import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateCompanyRecruitAreaCodeService {

    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;
    private final RecruitAreaFacade recruitAreaFacade;
    private final CodeFacade codeFacade;

    public void execute(UUID recruitAreaId, CreateRecruitAreaCodeRequest request) {

        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);
        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaByIdAndRecruitment(recruitAreaId, recruitment);
        List<Code> codeList = codeFacade.findAllCodeById(request.getCodeList());

        List<RecruitAreaCode> recruitAreaCodeList = codeList.stream()
                .map(code -> new RecruitAreaCode(recruitArea, code))
                .toList();

        recruitAreaCodeRepository.saveAll(recruitAreaCodeList);
    }
}
