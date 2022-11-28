package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest.Area;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.RequiredLicences;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import com.example.jobis.domain.recruit.domain.repository.RequiredLicencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyRecruitmentService {
    private final RecruitRepository recruitRepository;
    private final RecruitAreaRepository recruitAreaRepository;
    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final RequiredLicencesRepository requiredLicencesRepository;
    private final CompanyFacade companyFacade;
    private final CodeFacade codeFacade;

    @Transactional
    public void execute(ApplyRecruitmentRequest request) {
        Company company = companyFacade.getCurrentCompany();

        String hiringProgress = request.getHiringProgress()
                        .stream().map(Enum::toString)
                        .collect(Collectors.joining(","));

        Recruit recruit = recruitRepository.save(
                Recruit.builder()
                        .company(company)
                        .hiringProgress(hiringProgress)
                        .pay(request.getPay())
                        .trainPay(request.getTrainPay())
                        .benefit(request.getBenefits())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .recruitYear(LocalDate.now().getYear())
                        .status(RecruitStatus.REQUESTED)
                        .etc(request.getEtc())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .military(request.isMilitary())
                        .build()
        );

        codeFacade.findCodesWithValidation(request.getRequiredLicenses(), CodeType.LICENSE).forEach(
                code -> requiredLicencesRepository.save(
                        new RequiredLicences(code.getCode(), recruit)
                )
        );

        for(Area area : request.getAreas()) {
            RecruitArea recruitArea = recruitAreaRepository.save(
                    RecruitArea.builder()
                            .majorTask(area.getMajorTask())
                            .hiredCount(area.getHiring())
                            .recruit(recruit)

                            .build()
            );

            codeFacade.findCodesWithValidation(area.getJob(), CodeType.JOB).forEach(
                    code -> recruitAreaCodeRepository.save(
                            new RecruitAreaCode(recruitArea, code)
                    )
            );

            codeFacade.findCodesWithValidation(area.getTech(), CodeType.TECH).forEach(
                    code -> recruitAreaCodeRepository.save(
                            new RecruitAreaCode(recruitArea, code)
                    )
            );
        }
    }
}
