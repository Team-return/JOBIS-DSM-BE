package com.example.jobisapplication.domain.application.dto.response;

import com.example.jobisapplication.domain.application.spi.vo.TotalApplicationCountVO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryEmploymentCountResponse {

    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;

    public static QueryEmploymentCountResponse of(TotalApplicationCountVO vo) {
        return QueryEmploymentCountResponse.builder()
                .totalStudentCount(vo.getTotalStudentCount())
                .passedCount(vo.getPassedCount())
                .approvedCount(vo.getApprovedCount())
                .build();
    }
}
