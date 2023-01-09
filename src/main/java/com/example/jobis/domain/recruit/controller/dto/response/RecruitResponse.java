package com.example.jobis.domain.recruit.controller.dto.response;

import com.example.jobis.domain.code.controller.dto.response.CodeResponse;
import com.example.jobis.domain.recruit.domain.Recruit;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecruitResponse {

    private final Long recruitId;
    private final String companyName;
    private final String companyProfileUrl;
    private final Integer trainPay;
    private final boolean military;
    private final List<Long> codeList;
}
