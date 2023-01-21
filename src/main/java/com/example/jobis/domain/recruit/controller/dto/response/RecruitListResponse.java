package com.example.jobis.domain.recruit.controller.dto.response;

import com.example.jobis.domain.code.domain.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class RecruitListResponse {

    private final List<RecruitResponse> recruitList;

    @Getter
    @Builder
    public static class RecruitResponse {

        private final Long recruitId;
        private final String companyName;
        private final String companyProfileUrl;
        private final Integer trainPay;
        private final boolean military;
        private final Set<String> jobCodeList;
    }
}
