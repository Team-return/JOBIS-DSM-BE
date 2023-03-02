package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class QueryTeacherApplicationListResponse {

    private final UUID applicationId;
    private final String studentName;
    private final String studentNumber;
    private final List<String> applicationAttachmentUrl;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;
}
