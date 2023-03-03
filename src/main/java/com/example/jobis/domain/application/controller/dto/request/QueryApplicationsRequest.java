package com.example.jobis.domain.application.controller.dto.request;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class QueryApplicationsRequest {

    private final UUID recruitmentId;
    private final UUID studentId;
    private final ApplicationStatus neApplicationStatus;
    private final ApplicationStatus eqApplicationStatus;
    private final String studentName;
}
