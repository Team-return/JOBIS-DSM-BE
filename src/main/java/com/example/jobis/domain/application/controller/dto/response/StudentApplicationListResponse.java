package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudentApplicationListResponse {

    private final String companyName;
    private final ApplicationStatus applicationStatus;
    private final LocalDateTime createdAt;

    @Builder
    @QueryProjection
    public StudentApplicationListResponse(String companyName, ApplicationStatus applicationStatus, LocalDateTime createdAt) {
        this.companyName = companyName;
        this.applicationStatus = applicationStatus;
        this.createdAt = createdAt;
    }
}
