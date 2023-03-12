package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryRecruitmentsService {
    private final RecruitmentRepository recruitmentRepository;

    public TeacherQueryRecruitmentsResponse execute(String companyName, LocalDate start, LocalDate end,
                                                          Integer year, RecruitStatus status, Integer page) {
        List<TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(
                        year, start, end, status, companyName, page-1
                        ).stream()
                        .map(r ->
                                TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse.builder()
                                        .id(r.getRecruitment().getId())
                                        .recruitmentStatus(r.getRecruitment().getStatus())
                                        .companyName(r.getCompany().getName())
                                        .companyType(r.getCompany().getType())
                                        .start(r.getRecruitment().getRecruitDate().getStartDate())
                                        .end(r.getRecruitment().getRecruitDate().getFinishDate())
                                        .militarySupport(r.getRecruitment().getMilitarySupport())
                                        //.applicationCount(0) /// TODO('추후 학생 지원로직 개발')
                                        .recruitmentCount(r.getTotalHiring())
                                        .recruitmentJob(r.getRecruitAreaList())
                                        .build()
                        ).toList();

        return new TeacherQueryRecruitmentsResponse(recruitments);
    }
}
