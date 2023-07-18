package com.example.jobisapplication.domain.bug.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class BugReport {

    private final Long id;
    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
}
