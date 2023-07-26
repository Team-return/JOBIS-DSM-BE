package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentDetailVO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();

        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);


        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId());
        List<RecruitAreaResponse> recruitAreaResponses =
                queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId());
        return QueryRecruitmentDetailResponse.of(recruitmentDetail, recruitAreaResponses);
    }
}
