package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse.RecruitResponse;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitAreaCodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRecruitListService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public RecruitListResponse execute() {
        List<RecruitResponse> recruitList = recruitmentRepository.findAll()
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
                .jobCodeList(getCodeList(recruitment.getRecruitAreaList()))
                .build();
    }

    private List<String> getCodeList(List<RecruitArea> recruitArea) {
        List<String> res = new ArrayList<>();
        for(RecruitArea ra: recruitArea) {
            res.addAll(recruitmentRepository.queryKeywordListByRecruitArea(ra).stream()
                    .map(QueryRecruitAreaCodeVO::getKeyword).toList()
            );
        }
        return res;
    }
}
