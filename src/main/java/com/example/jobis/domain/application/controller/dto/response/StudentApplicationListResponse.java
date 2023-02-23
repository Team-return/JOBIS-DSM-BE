package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class StudentApplicationListResponse {

    private final UUID applicationId;
    private final String student;
    private final String company;
    private final List<String> urlList;
    private final ApplicationStatus applicationStatus;

    @QueryProjection
    public StudentApplicationListResponse(UUID applicationId, String student, String company, List<String> urlList, ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.student = student;
        this.company = company;
        this.urlList = urlList;
        this.applicationStatus = applicationStatus;
    }
}
