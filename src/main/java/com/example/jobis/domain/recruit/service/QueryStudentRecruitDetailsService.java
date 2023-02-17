package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.recruit.controller.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruit.controller.dto.response.StudentRecruitDetailsResponse.RecruitAreaResponse;
import com.example.jobis.domain.recruit.domain.RecruitArea;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryStudentRecruitDetailsService {

    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public StudentRecruitDetailsResponse execute(UUID recruitId) {

        Recruitment recruitment = recruitFacade.getRecruitById(recruitId);

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
                .filter(recruitAreaCode -> recruitAreaCode.getCodeId().getCodeType().equals(CodeType.JOB))
                .map(recruitAreaCode -> recruitAreaCode.getCodeId().getKeyword())
                .toList();
        List<String> techCodes = recruitArea.getCodeList().stream()
                .filter(recruitAreaCode -> recruitAreaCode.getCodeId().getCodeType().equals(CodeType.TECH))
                .map(recruitAreaCode -> recruitAreaCode.getCodeId().getKeyword())
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
