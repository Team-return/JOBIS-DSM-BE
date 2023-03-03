package com.example.jobis.domain.application.domain.repository.vo;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class QueryApplicationVO {

    private final UUID applicationId;
    private final String studentName;
    private final String studentNumber;
    private final String companyName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;

    @QueryProjection
    public QueryApplicationVO(UUID applicationId, String studentName, String studentNumber,
                              String companyName, List<String> applicationAttachmentUrl, LocalDateTime createdAt, ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.companyName = companyName;
        this.applicationAttachmentUrl = applicationAttachmentUrl;
        this.createdAt = createdAt;
        this.applicationStatus = applicationStatus;
    }
}
