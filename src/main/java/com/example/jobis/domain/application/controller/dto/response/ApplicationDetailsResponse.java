package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.Set;

@Getter
public class ApplicationDetailsResponse {

    private final String student;
    private final String company;
    private final Set<String> urlList;
    private final ApplicationStatus applicationStatus;

    @QueryProjection
    public ApplicationDetailsResponse(String student, String company, Set<String> urlList, ApplicationStatus applicationStatus) {
        this.student = student;
        this.company = company;
        this.urlList = urlList;
        this.applicationStatus = applicationStatus;
    }
}
