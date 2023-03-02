package com.example.jobis.domain.application.controller.dto.response;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class StudentApplicationListResponse {

    private final UUID applicationId;
    private final String student;
    private final String company;
    private final List<String> attachmentUrlList;
    private final ApplicationStatus applicationStatus;
}
