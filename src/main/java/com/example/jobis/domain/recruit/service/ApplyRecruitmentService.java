package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest.Area;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyRecruitmentService {
    private final RecruitRepository recruitRepository;
    private final RecruitAreaRepository recruitAreaRepository;
    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final CompanyFacade companyFacade;
    private final CodeFacade codeFacade;

    @Transactional
    public void execute(ApplyRecruitmentRequest request) {
        Company company = companyFacade.getCurrentCompany();

        String hiringProgress = request.getHiringProgress()
                        .stream().map(Enum::toString)
                        .collect(Collectors.joining(","));

        String requiredLicenses = request.getRequiredLicenses() == null?
                null : String.join(",", request.getRequiredLicenses());

        Recruitment recruitment = recruitRepository.save(
                Recruitment.builder()
                        .company(company)
                        .hiringProgress(hiringProgress)
                        .workingHours(request.getWorkHours())
                        .pay(request.getPay())
                        .trainPay(request.getTrainPay())
                        .benefit(request.getBenefits())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .recruitYear(LocalDate.now().getYear())
                        .requiredLicenses(requiredLicenses)
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .submitDocument(request.getSubmitDocument())
                        .etc(request.getEtc())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .militarySupport(request.isMilitary())
                        .build()
        );

        List<Long> requestCode = new ArrayList<>();
        for(Area area : request.getAreas()) {
            RecruitArea recruitArea = recruitAreaRepository.save(
                    RecruitArea.builder()
                            .majorTask(area.getMajorTask())
                            .hiredCount(area.getHiring())
                            .recruitment(recruitment)
                            .build()
            );
            requestCode.addAll(area.getJob());
            requestCode.addAll(area.getTech());

            List<Code> codeList = codeFacade.findAllCodeById(requestCode);
            List<RecruitAreaCode> recruitAreaCodes = codeList.stream()
                    .map(c -> new RecruitAreaCode(recruitArea, c))
                    .collect(Collectors.toList());
            recruitAreaCodeRepository.saveAll(recruitAreaCodes);
        }
    }
}
