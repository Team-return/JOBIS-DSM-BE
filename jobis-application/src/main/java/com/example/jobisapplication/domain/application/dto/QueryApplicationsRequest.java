package com.example.jobisapplication.domain.application.dto;

import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryApplicationsRequest {

    private final Long recruitmentId;

    private final Long studentId;

    private final ApplicationStatus applicationStatus;

    private final String studentName;

    private Long companyId;
}
