package com.example.jobisapplication.domain.application.spi.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TotalApplicationCountVO {
    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;
}
