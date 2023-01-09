package com.example.jobis.domain.recruit.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitListResponse {

    private final List<RecruitResponse> recruitList;

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
}
