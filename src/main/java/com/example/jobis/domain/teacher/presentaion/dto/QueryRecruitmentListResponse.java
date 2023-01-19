package com.example.jobis.domain.teacher.presentaion.dto;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryRecruitmentListResponse {
    private final List<TeacherRecruitmentResponse> recruitmentList;


    @Getter
    @Builder
    public static class TeacherRecruitmentResponse{
        private RecruitStatus recruitmentStatus;
        private String companyName;
        private List<String> recruitmentJob;
        private CompanyType companyType;
        private int totalRecruitmentCount;
        private int currentApplicantCount;
        private LocalDate recruitmentStartDate;
        private LocalDate recruitmentEndDate;
    }
}
