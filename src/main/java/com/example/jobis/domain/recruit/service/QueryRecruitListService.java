package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRecruitListService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public List<RecruitListResponse> execute(String name, Integer page) {
        return recruitmentRepository.queryRecruitmentsByConditions(Year.now().getValue(), null, null,
                        RecruitStatus.RECRUITING, name, page-1).stream()
                .map(
                        r -> RecruitListResponse.builder()
                                .recruitId(r.getRecruitment().getId())
                                .companyName(r.getCompany().getName())
                                .trainPay(r.getRecruitment().getPay().getTrainingPay())
                                .jobCodeList(r.getRecruitAreaList())
                                .military(r.getRecruitment().isMilitarySupport())
                                .companyProfileUrl(r.getCompany().getCompanyLogoUrl())
                                .totalHiring(r.getTotalHiring())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
