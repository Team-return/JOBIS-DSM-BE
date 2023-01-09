package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitDetailsResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitDetailsResponse.RecruitAreaResponse;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryRecruitDetailsService {

    private final RecruitFacade recruitFacade;
    private final CodeFacade codeFacade;

    @Transactional(readOnly = true)
    public RecruitDetailsResponse execute(Long recruitId) {
        Recruit recruit = recruitFacade.getRecruitById(recruitId);

        List<RecruitAreaResponse> recruitAreaList = recruit.getRecruitAreaList().stream()
                .map(this::recruitAreaBuilder)
                .toList();

        return RecruitDetailsResponse.builder()
                .areas(recruitAreaList)
                .preferentialTreatment(recruit.getPreferentialTreatment())
                .requiredGrade(recruit.getRequiredGrade())
                .workHours(recruit.getWorkHours())
                .requiredLicenses(recruit.getRequiredLicenses())
                .hiringProgress(recruit.getHiringProgress())
                .trainPay(recruit.getPay().getTrainPay())
                .pay(recruit.getPay().getPay())
                .benefits(recruit.getBenefit())
                .military(recruit.isMilitary())
                .submitDocument(recruit.getSubmitDocument())
                .startDate(recruit.getRecruitDate().getStartDate())
                .endDate(recruit.getRecruitDate().getEndDate())
                .etc(recruit.getEtc())
                .build();
    }

    public RecruitAreaResponse recruitAreaBuilder(RecruitArea recruitArea) {

        List<String> jobCodes = codeFacade.getKeywordByRecruitArea(recruitArea, CodeType.JOB);
        List<String> techCodes = codeFacade.getKeywordByRecruitArea(recruitArea, CodeType.TECH);

        return RecruitAreaResponse.builder()
                .job(jobCodes)
                .tech(techCodes)
                .hiring(recruitArea.getHiredCount())
                .majorTask(recruitArea.getMajorTask())
                .build();
    }
}
