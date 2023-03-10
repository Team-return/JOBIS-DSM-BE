package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.recruitment.controller.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruitment.controller.dto.response.StudentRecruitDetailsResponse.RecruitAreaResponse;
import com.example.jobis.domain.recruitment.domain.RecruitArea;

import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StudentQueryRecruitmentDetailService {

    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public StudentRecruitDetailsResponse execute(UUID recruitId) {

        Recruitment recruitment = recruitFacade.queryRecruitmentById(recruitId);

        List<RecruitAreaResponse> recruitAreaList = recruitment.getRecruitAreaList().stream()
                .map(this::recruitAreaBuilder)
                .toList();

        return StudentRecruitDetailsResponse.builder()
                .areas(recruitAreaList)
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .requiredGrade(recruitment.getRequiredGrade())
                .workHours(recruitment.getWorkingHours())
                .requiredLicenses(recruitment.getRequiredLicenses())
                .hiringProgress(recruitment.getHiringProgress())
                .trainPay(recruitment.getPay().getTrainingPay())
                .pay(recruitment.getPay().getPay())
                .benefits(recruitment.getBenefit())
                .military(recruitment.isMilitarySupport())
                .submitDocument(recruitment.getSubmitDocument())
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .etc(recruitment.getEtc())
                .build();
    }

    public RecruitAreaResponse recruitAreaBuilder(RecruitArea recruitArea) {

        List<String> jobCodes = recruitArea.getCodeList().stream()
                .filter(recruitAreaCode -> recruitAreaCode.getCodeType().equals(CodeType.JOB))
                .map(RecruitAreaCode::getCodeKeyword)
                .toList();
        List<String> techCodes = recruitArea.getCodeList().stream()
                .filter(recruitAreaCode -> recruitAreaCode.getCodeType().equals(CodeType.TECH))
                .map(RecruitAreaCode::getCodeKeyword)
                .toList();

        return RecruitAreaResponse.builder()
                .recruitAreaId(recruitArea.getId())
                .job(jobCodes)
                .tech(techCodes)
                .hiring(recruitArea.getHiredCount())
                .majorTask(recruitArea.getMajorTask())
                .build();
    }
}
