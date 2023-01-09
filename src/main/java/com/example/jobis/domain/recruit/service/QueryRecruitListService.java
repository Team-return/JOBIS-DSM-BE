package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.controller.dto.response.CodeResponse;
import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitResponse;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRecruitListService {

    private final RecruitRepository recruitRepository;
    private final CodeFacade codeFacade;

    @Transactional(readOnly = true)
    public RecruitListResponse execute() {

        List<RecruitResponse> recruitList = recruitRepository.findAll()
                .stream()
                .map(this::RecruitBuilder)
                .collect(Collectors.toList());

        return new RecruitListResponse(recruitList);
    }

    private RecruitResponse RecruitBuilder(Recruit recruit) {
        List<Long> codeList = new ArrayList<>();
        for (RecruitArea recruitArea: recruit.getRecruitAreaList()) {
            codeList.addAll(recruitArea.getCodeList().stream()
                    .map(RecruitAreaCode::getCodeId)
                    .map(Code::getCode)
                    .toList());
        }

        return RecruitResponse.builder()
                .recruitId(recruit.getId())
                .companyName(recruit.getCompany().getCompanyName())
                .companyProfileUrl(recruit.getCompany().getCompanyProfileUrl())
                .military(recruit.isMilitary())
                .trainPay(recruit.getPay().getTrainPay())
                .codeList(codeList)
                .build();
    }


}
