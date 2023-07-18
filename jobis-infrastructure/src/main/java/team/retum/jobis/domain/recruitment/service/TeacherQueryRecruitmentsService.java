package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.recruitment.domain.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.presentation.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryRecruitmentsService {

    private final RecruitmentRepository recruitmentRepository;

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
                recruitmentRepository.getRecruitmentCountByCondition(recruitmentFilter).doubleValue() / 11
        );

        List<TeacherRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(recruitmentFilter).stream()
                        .map(recruitment ->
                                TeacherRecruitmentResponse.builder()
                                        .id(recruitment.getRecruitmentId())
                                        .recruitmentStatus(recruitment.getRecruitStatus())
                                        .companyName(recruitment.getCompanyName())
                                        .companyType(recruitment.getCompanyType())
                                        .start(recruitment.getRecruitDate().getStartDate())
                                        .end(recruitment.getRecruitDate().getFinishDate())
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
