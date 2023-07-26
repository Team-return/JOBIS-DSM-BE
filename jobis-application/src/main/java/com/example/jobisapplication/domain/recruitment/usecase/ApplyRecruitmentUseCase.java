package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.common.util.StringUtil;
import com.example.jobisapplication.domain.code.model.RecruitAreaCode;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentAlreadyExistsException;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ApplyRecruitmentUseCase {

    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final QueryCodePort queryCodePort;
    private final SecurityPort securityPort;

    public void execute(ApplyRecruitmentRequest request) {
        Long currentCompanyId = securityPort.getCurrentUserId();
        checkRecruitmentExists(currentCompanyId);

        Recruitment recruitment = commandRecruitmentPort.saveRecruitment(
                Recruitment.builder()
                        .companyId(currentCompanyId)
                        .recruitYear(Year.now().getValue())
                        .militarySupport(request.isMilitarySupport())
                        .personalContract(request.isPersonalContact())
                        .workingHours(request.getWorkHours())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .requiredLicenses(request.getRequiredLicenses())
                        .status(RecruitStatus.REQUESTED)
                        .requiredGrade(request.getRequiredGrade())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .trainPay(request.getTrainPay())
                        .submitDocument(request.getSubmitDocument())
                        .pay(request.getPay())
                        .benefits(request.getBenefits())
                        .etc(request.getEtc())
                        .hiringProgress(request.getHiringProgress())
                        .build()
        );

        request.getAreas()
                .forEach(area -> {
                    String jobCodes =
                            StringUtil.joinStringList(queryCodePort.queryCodesByIdIn(area.getJobCodes()));

                    RecruitArea savedRecruitmentArea = commandRecruitmentPort.saveRecruitmentArea(
                            RecruitArea.builder()
                                    .recruitmentId(recruitment.getId())
                                    .jobCodes(jobCodes)
                                    .hiredCount(area.getHiring())
                                    .majorTask(area.getMajorTask())
                                    .build()
                    );

                    List<RecruitAreaCode> recruitAreaCodes = area.getTechCodes().stream()
                            .map(
                                    code -> RecruitAreaCode.builder()
                                            .recruitAreaId(savedRecruitmentArea.getId())
                                            .codeId(code)
                                            .build()
                            ).toList();
                    commandRecruitmentPort.saveAllRecruitmentAreaCodes(recruitAreaCodes);
                });
    }

    private void checkRecruitmentExists(Long companyId) {
        if (queryRecruitmentPort.existsOnRecruitmentByCompanyId(companyId)) {
            throw RecruitmentAlreadyExistsException.EXCEPTION;
        }
    }
}
