package com.example.jobisapplication.domain.application.spi.vo;

import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationVO {

    private final Long id;
    private final String name;
    private final Integer grade;
    private final Integer number;
    private final Integer classNumber;
    private final String profileImageUrl;
    private final String companyName;
    private final List<ApplicationAttachment> applicationAttachmentEntities;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;
}
