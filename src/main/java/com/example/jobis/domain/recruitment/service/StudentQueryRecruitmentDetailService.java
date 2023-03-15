package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse.RecruitAreaResponse;
import com.example.jobis.global.annotation.ReadOnlyService;
import com.example.jobis.global.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentDetailService {
    private final RecruitmentRepository recruitmentRepository;

    public StudentRecruitDetailsResponse execute(UUID recruitId) {
        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return StudentRecruitDetailsResponse.builder()
                .areas(queryRecruitAreas(recruitment.getId()))
                .pay(recruitment.getPayInfo().getPay())
                .trainPay(recruitment.getPayInfo().getTrainingPay())
                .etc(recruitment.getEtc())
                .requiredGrade(recruitment.getRequiredGrade())
                .requiredLicenses(StringUtil.divideString(recruitment.getRequiredLicenses()))
                .startDate(recruitment.getRecruitDate().getStartDate())
                .endDate(recruitment.getRecruitDate().getFinishDate())
                .hiringProgress(StringUtil.divideString(recruitment.getHiringProgress()))
                .workHours(recruitment.getWorkingHours())
                .preferentialTreatment(recruitment.getPreferentialTreatment())
                .military(recruitment.getMilitarySupport())
                .benefits(recruitment.getBenefits())
                .build();
    }

    private List<RecruitAreaResponse> queryRecruitAreas(UUID recruitmentId) {
        return recruitmentRepository.queryRecruitAreasByRecruitmentId(recruitmentId).stream()
                .map(recruitArea ->
                                RecruitAreaResponse.builder()
                                        .recruitAreaId(recruitArea.getId())
                                        .majorTask(recruitArea.getMajorTask())
                                        .hiring(recruitArea.getHiredCount())
                                        .tech(
                                                recruitArea.getCodeList().stream()
                                                .filter(recruitAreaCode -> recruitAreaCode.getCodeType() == CodeType.TECH)
                                                .map(RecruitAreaCode::getCodeKeyword).toList()
                                        )
                                        .job(
                                                recruitArea.getCodeList().stream()
                                                        .filter(recruitAreaCode -> recruitAreaCode.getCodeType() == CodeType.JOB)
                                                        .map(RecruitAreaCode::getCodeKeyword).toList()
                                        )
                                        .build()
                ).toList();
    }
}
