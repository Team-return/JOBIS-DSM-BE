package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class QueryApplicationListResponse {

    private final UUID applicationId;
    private final String studentName;
    private final String studentNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;

    @QueryProjection
    public QueryApplicationListResponse(UUID applicationId, String studentName, String studentNumber, LocalDateTime createdAt, ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.createdAt = createdAt;
        this.applicationStatus = applicationStatus;

    }
}
