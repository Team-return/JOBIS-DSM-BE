package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateCompanyRecruitAreaService {

    private final RecruitAreaRepository recruitAreaRepository;
    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final CodeFacade codeFacade;
    private final RecruitFacade recruitFacade;
    private final CompanyFacade companyFacade;

    @Transactional
    public void execute(CreateRecruitAreaRequest request) {

        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);

        RecruitArea recruitArea = recruitAreaRepository.save(RecruitArea.builder()
                .majorTask(request.getMajorTask())
                .hiredCount(request.getHiring())
                .recruitment(recruitment)
                .build()
        );
        List<Long> codes = new ArrayList<>();
        codes.addAll(request.getTech());
        codes.addAll(request.getJob());
        List<Code> codeList = codeFacade.findAllCodeById(codes);
        List<RecruitAreaCode> recruitAreaCodeList = codeList.stream()
                .map(code -> new RecruitAreaCode(recruitArea, code))
                .toList();
        recruitAreaCodeRepository.saveAll(recruitAreaCodeList);
    }
}
