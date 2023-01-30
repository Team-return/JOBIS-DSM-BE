package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse.RecruitResponse;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
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
    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public RecruitListResponse execute(String name, Integer page) {
        List<RecruitResponse> recruitList = recruitmentRepository.queryRecruitmentsByConditions(
                        Year.now().getValue(), null, null, RecruitStatus.ON_RECRUIT, name, page-1
                )
                .stream().map(this::recruitmentBuilder)
                .collect(Collectors.toList());
        return new RecruitListResponse(recruitList);
    }

    private RecruitResponse recruitmentBuilder(Recruitment recruitment) {
        return RecruitResponse.builder()
                .recruitId(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .companyProfileUrl(recruitment.getCompany().getCompanyLogoUrl())
                .military(recruitment.isMilitarySupport())
                .trainPay(recruitment.getPay().getTrainingPay())
                .jobCodeList(recruitFacade.getJobCodeList(recruitment.getRecruitAreaList()))
                .build();
    }


}
