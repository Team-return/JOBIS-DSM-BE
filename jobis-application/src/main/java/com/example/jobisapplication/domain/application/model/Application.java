package com.example.jobisapplication.domain.application.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@Aggregate
public class Application {

    private final Long id;

    private final Long studentId;

    private final Long recruitmentId;

    private final ApplicationStatus applicationStatus;

    private final String rejectionReason;

    private final LocalDate startDate;

    private final LocalDate endDate;
}
