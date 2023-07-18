package com.example.jobisapplication.domain.bug.dto;

import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateBugReportRequest {

    private String title;

    private String content;

    private DevelopmentArea developmentArea;

    private List<String> attachmentUrls;
}
