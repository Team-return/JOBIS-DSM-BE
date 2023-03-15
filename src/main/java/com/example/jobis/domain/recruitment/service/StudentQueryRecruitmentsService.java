package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryRecruitmentsService {
    private final RecruitmentRepository recruitmentRepository;

    public StudentQueryRecruitmentsResponse execute(String name, Integer page) {
        List<StudentQueryRecruitmentsResponse.StudentRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(
                        Year.now().getValue(), null, null, RecruitStatus.RECRUITING, name, page-1
                        ).stream()
                        .map(
                                r -> StudentQueryRecruitmentsResponse.StudentRecruitmentResponse.builder()
                                        .recruitId(r.getRecruitment().getId())
                                        .companyName(r.getCompany().getName())
                                        .trainPay(r.getRecruitment().getPayInfo().getTrainingPay())
                                        .jobCodeList(r.getRecruitAreaList())
                                        .military(r.getRecruitment().getMilitarySupport())
                                        .companyProfileUrl(r.getCompany().getCompanyLogoUrl())
                                        .totalHiring(r.getTotalHiring())
                                        .build()
                ).toList();

        return new StudentQueryRecruitmentsResponse(recruitments);
    }

}
