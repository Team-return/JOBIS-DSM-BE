package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.recruitment.dto.RecruitmentFilter;
import com.example.jobisapplication.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse;
import com.example.jobisapplication.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryRecruitmentsUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;

    public TeacherQueryRecruitmentsResponse execute(String companyName, LocalDate start, LocalDate end,
                                                    Integer year, RecruitStatus status, Integer page) {

        RecruitmentFilter recruitmentFilter = RecruitmentFilter.builder()
                .companyName(companyName)
                .status(status)
                .startDate(start)
                .endDate(end)
                .year(year)
                .page(page)
                .build();

        int totalPageCount = (int) Math.ceil(
                queryRecruitmentPort.getRecruitmentCountByFilter(recruitmentFilter).doubleValue() / 11
        );

        List<TeacherRecruitmentResponse> recruitments =
                queryRecruitmentPort.queryRecruitmentsByFilter(recruitmentFilter).stream()
                        .map(recruitment ->
                                TeacherRecruitmentResponse.builder()
                                        .id(recruitment.getRecruitmentId())
                                        .recruitmentStatus(recruitment.getRecruitStatus())
                                        .companyName(recruitment.getCompanyName())
                                        .companyType(recruitment.getCompanyType())
                                        .start(recruitment.getStartDate())
                                        .end(recruitment.getEndDate())
                                        .militarySupport(recruitment.isMilitarySupport())
                                        .applicationRequestedCount(recruitment.getRequestedApplicationCount())
                                        .applicationApprovedCount(recruitment.getApprovedApplicationCount())
                                        .recruitmentCount(recruitment.getTotalHiring())
                                        .recruitmentJob(recruitment.getRecruitAreaList())
                                        .build()
                        ).toList();

        return new TeacherQueryRecruitmentsResponse(recruitments, totalPageCount);
    }
}
