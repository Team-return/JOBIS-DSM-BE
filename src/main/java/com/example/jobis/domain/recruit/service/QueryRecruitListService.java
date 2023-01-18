package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse.RecruitResponse;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
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
                .stream()
                .map(this::RecruitBuilder)
                .collect(Collectors.toList());

        return new RecruitListResponse(recruitList);
    }

    private RecruitResponse RecruitBuilder(Recruitment recruitment) {
        List<Long> codeList = new ArrayList<>();
        for (RecruitArea recruitArea: recruitment.getRecruitAreaList()) {
            codeList.addAll(recruitArea.getCodeList().stream()
                    .map(RecruitAreaCode::getCodeId)
                    .map(Code::getCode)
                    .toList());
        }

        return RecruitResponse.builder()
                .recruitId(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .companyProfileUrl(recruitment.getCompany().getCompanyLogoUrl())
                .military(recruitment.isMilitarySupport())
                .trainPay(recruitment.getPay().getTrainingPay())
                .codeList(codeList)
                .build();
    }


}
