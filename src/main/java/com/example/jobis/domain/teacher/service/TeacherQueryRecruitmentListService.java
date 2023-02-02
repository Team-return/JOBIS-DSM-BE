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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherQueryRecruitmentListService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public List<TQueryRecruitmentListResponse> execute(String companyName, LocalDate start, LocalDate end,
                                                       Integer year, RecruitStatus status, Integer page) {
        return recruitmentRepository.queryRecruitmentsByConditions(year, start, end, status, companyName, page-1).stream()
                .map(
                        r -> TQueryRecruitmentListResponse.builder()
                                .id(r.getId())
                                .recruitmentStatus(r.getStatus())
                                .companyName(r.getCompanyName())
                                .companyType(r.getCompanyType())
                                .start(r.getStart())
                                .end(r.getEnd())
                                .militarySupport(r.isMilitarySupport())
                                .applicationCount(0)
                                .recruitmentCount(r.getTotalHiring())
                                .recruitmentJob(r.getRecruitAreaList())
                                .build()
                ).collect(Collectors.toList());
    }
}
