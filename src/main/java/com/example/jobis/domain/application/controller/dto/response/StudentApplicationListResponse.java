package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class StudentApplicationListResponse {

    private final UUID applicationId;
    private final String companyName;
    private final ApplicationStatus applicationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    @Builder
    @QueryProjection
    public StudentApplicationListResponse(UUID applicationId, String companyName, ApplicationStatus applicationStatus, LocalDateTime createdAt) {
        this.applicationId = applicationId;
        this.companyName = companyName;
        this.applicationStatus = applicationStatus;
        this.createdAt = createdAt;
    }
}
