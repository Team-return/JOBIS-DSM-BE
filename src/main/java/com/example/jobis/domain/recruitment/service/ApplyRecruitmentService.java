package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest.Area;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.Service;
import com.example.jobis.global.util.StringUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ApplyRecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;

    public void execute(ApplyRecruitmentRequest request) {
        UUID currentUserId = userFacade.getCurrentUserId();
        Company company = companyRepository.queryCompanyById(currentUserId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        String hiringProgress = StringUtil.getHiringProgress(request.getHiringProgress());
        String requiredLicenses = StringUtil.getRequiredLicenses(request.getRequiredLicenses());

        Recruitment recruitment = recruitmentRepository.saveRecruitment(
                Recruitment.builder()
                        .company(company)
                        .recruitYear(LocalDate.now().getYear())
                        .militarySupport(request.getMilitarySupport())
                        .workingHours(request.getWorkHours())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .requiredLicenses(requiredLicenses)
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .trainPay(request.getTrainPay())
                        .submitDocument(request.getSubmitDocument())
                        .pay(request.getPay())
                        .benefit(request.getBenefits())
                        .etc(request.getEtc())
                        .hiringProgress(hiringProgress)
                        .build()
        );

        for(Area area : request.getAreas()) {
            RecruitArea recruitArea = recruitmentRepository.saveRecruitArea(
                    RecruitArea.builder()
                            .majorTask(area.getMajorTask())
                            .hiredCount(area.getHiring())
                            .recruitment(recruitment)
                            .build()
            );

            List<Code> codes = codeFacade.findAllCodeById(
                    Stream.of(area.getJob(), area.getTech())
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList())
            );

            recruitmentRepository.saveAllRecruitAreaCodes(
                    codeFacade.generateRecruitAreaCode(recruitArea, codes)
            );
        }
    }
}
