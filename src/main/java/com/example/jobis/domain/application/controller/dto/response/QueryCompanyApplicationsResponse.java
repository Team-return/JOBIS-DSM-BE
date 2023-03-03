package com.example.jobis.domain.application.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class QueryCompanyApplicationsResponse {

    private final UUID applicationId;
    private final String studentNumber;
    private final String studentName;
    private final List<String> applicationAttachmentUrl;
    @JsonFormat(pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
}
