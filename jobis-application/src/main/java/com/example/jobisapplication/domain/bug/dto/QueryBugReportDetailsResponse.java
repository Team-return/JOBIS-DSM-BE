package com.example.jobisapplication.domain.bug.dto;

import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QueryBugReportDetailsResponse {

    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final List<String> attachments;
    private final LocalDateTime createdAt;

}
