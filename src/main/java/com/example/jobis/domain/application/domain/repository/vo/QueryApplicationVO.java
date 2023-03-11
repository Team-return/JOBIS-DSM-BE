package com.example.jobis.domain.application.domain.repository.vo;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class QueryApplicationVO {

    private final UUID id;
    private final String name;
    private final int grade;
    private final int number;
    private final int classNumber;
    private final String companyName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;

    @QueryProjection

    public QueryApplicationVO(UUID id, String name, int grade, int number, int classNumber,
                              String companyName, List<String> applicationAttachmentUrl, LocalDateTime createdAt,
                              ApplicationStatus applicationStatus) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.classNumber = classNumber;
        this.companyName = companyName;
        this.applicationAttachmentUrl = applicationAttachmentUrl;
        this.createdAt = createdAt;
        this.applicationStatus = applicationStatus;
    }
}
