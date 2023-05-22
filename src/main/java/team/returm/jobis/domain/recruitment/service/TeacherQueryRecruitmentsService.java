package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.presentation.dto.RecruitmentFilter;
import team.returm.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

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
                        .map(vo ->
                                TeacherRecruitmentResponse.builder()
                                        .id(vo.getRecruitment().getId())
                                        .recruitmentStatus(vo.getRecruitment().getStatus())
                                        .companyName(vo.getCompany().getName())
                                        .companyType(vo.getCompany().getType())
                                        .start(vo.getRecruitment().getRecruitDate().getStartDate())
                                        .end(vo.getRecruitment().getRecruitDate().getFinishDate())
                                        .militarySupport(vo.getRecruitment().getMilitarySupport())
                                        .applicationRequestedCount(vo.getRequestedApplicationCount())
                                        .applicationApprovedCount(vo.getApprovedApplicationCount())
                                        .recruitmentCount(vo.getTotalHiring())
                                        .recruitmentJob(vo.getRecruitAreaList())
                                        .build()
                        ).toList();

        return new TeacherQueryRecruitmentsResponse(recruitments, totalPageCount);
    }
}
