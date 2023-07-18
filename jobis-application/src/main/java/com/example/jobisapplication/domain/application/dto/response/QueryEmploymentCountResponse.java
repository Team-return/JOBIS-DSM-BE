package com.example.jobisapplication.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryTotalApplicationCountVO;

@Getter
@Builder
public class QueryEmploymentCountResponse {

    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;

    public static QueryEmploymentCountResponse of(QueryTotalApplicationCountVO vo) {
        return QueryEmploymentCountResponse.builder()
                .totalStudentCount(vo.getTotalStudentCount())
                .passedCount(vo.getPassedCount())
                .approvedCount(vo.getApprovedCount())
                .build();
    }
}
