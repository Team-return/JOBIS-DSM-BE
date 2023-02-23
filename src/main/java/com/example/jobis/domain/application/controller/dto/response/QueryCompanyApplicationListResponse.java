package com.example.jobis.domain.application.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryCompanyApplicationListResponse {

    private final String studentNumber;
    private final String studentName;
    private final List<String> applicationAttachmentUrl;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryCompanyApplicationListResponse(String studentNumber, String studentName, List<String> applicationAttachmentUrl, LocalDateTime createdAt) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.applicationAttachmentUrl = applicationAttachmentUrl;
        this.createdAt = createdAt;
    }
}
