package team.returm.jobis.domain.recruitment.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.exception.InvalidDateFilterRangeException;
import team.returm.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryRecruitmentsService {
    private final RecruitmentRepository recruitmentRepository;

    public TeacherQueryRecruitmentsResponse execute(String companyName, LocalDate start, LocalDate end,
                                                    Integer year, RecruitStatus status, Integer page) {

        List<TeacherQueryRecruitmentsResponse.TeacherRecruitmentResponse> recruitments =
                recruitmentRepository.queryRecruitmentsByConditions(
                                year, start, end, status, companyName, page - 1, null
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
                                        .applicationCount(r.getApplicationCount())
                                        .recruitmentCount(r.getTotalHiring())
                                        .recruitmentJob(r.getRecruitAreaList())
                                        .build()
                        ).toList();

        return new TeacherQueryRecruitmentsResponse(recruitments);
    }
}
