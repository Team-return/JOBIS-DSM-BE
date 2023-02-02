package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import com.example.jobis.domain.teacher.presentaion.dto.response.TQueryRecruitmentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherQueryRecruitmentListService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public List<TQueryRecruitmentListResponse> execute(String companyName, LocalDate start, LocalDate end,
                                                       Integer year, RecruitStatus status, Integer page) {
        return recruitmentRepository.queryRecruitmentsByConditions(year, start, end, status, companyName, page-1).stream()
                .map(
                        r -> TQueryRecruitmentListResponse.builder()
                                .id(r.getId())
                                .recruitmentStatus(r.getStatus())
                                .militarySupport(r.isMilitarySupport())
                                //.applicationCount() /// TODO: 2023/01/28 추후 학생 지원로직 개발
                                .recruitmentJob(recruitFacade.getJobCodeList(r.getRecruitAreaList()))
                                .recruitmentCount(getTotalRecruitCount(r.getRecruitAreaList()))
                                .companyName(r.getCompany().getName())
                                .companyType(r.getCompany().getType())
                                .start(r.getRecruitDate().getStartDate())
                                .end(r.getRecruitDate().getFinishDate())
                                .build()
                ).collect(Collectors.toList());
    }

    private Integer getTotalRecruitCount(List<RecruitArea> areas) {
        return areas.stream().mapToInt(RecruitArea :: getHiredCount).sum();
    }
}
