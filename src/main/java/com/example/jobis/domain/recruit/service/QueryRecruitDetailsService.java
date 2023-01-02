package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.recruit.controller.dto.response.RecruitDetailsResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitDetailsResponse.RecruitAreaResponse;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRecruitDetailsService {

    private final RecruitFacade recruitFacade;
    private final RecruitAreaRepository recruitAreaRepository;

    @Transactional(readOnly = true)
    public RecruitDetailsResponse execute(Long recruitId) {
        Recruit recruit = recruitFacade.getRecruitById(recruitId);

        List<RecruitAreaResponse> recruitAreaList = recruitAreaRepository.findAllByRecruit(recruit).stream()
                .map(RecruitAreaResponse::of)
                .toList();

        return RecruitDetailsResponse.builder()
                .areas(recruitAreaList)
                .preferentialTreatment(recruit.getPreferentialTreatment())
                .requiredGrade(recruit.getRequiredGrade())
                .workHours(recruit.getWorkHours())
                .requiredLicenses(recruit.getRequiredLicencesList().stream()
                        .map(r -> r.getLicenseCode().toString())
                        .collect(Collectors.toList())
                )
                .hiringProgress(recruit.getHiringProgress())
                .trainPay(recruit.getPay().getTrainPay())
                .pay(recruit.getPay().getPay())
                .benefits(recruit.getBenefit())
                .military(recruit.isMilitary())
                .submitDocument(recruit.getSubmitDocument())
                .startDate(recruit.getRecruitDate().getStartDate())
                .endDate(recruit.getRecruitDate().getEndDate())
                .etc(recruit.getEtc())
                .build();
    }
}
