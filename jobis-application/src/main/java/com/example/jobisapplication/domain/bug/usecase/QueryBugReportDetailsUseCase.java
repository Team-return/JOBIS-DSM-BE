package com.example.jobisapplication.domain.bug.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.bug.dto.QueryBugReportDetailsResponse;
import com.example.jobisapplication.domain.bug.exception.BugReportNotFoundException;
import com.example.jobisapplication.domain.bug.model.BugAttachment;
import com.example.jobisapplication.domain.bug.model.BugReport;
import com.example.jobisapplication.domain.bug.spi.QueryBugReportPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryBugReportDetailsUseCase {

    private final QueryBugReportPort queryBugReportPort;

    public QueryBugReportDetailsResponse execute(Long bugReportId) {
        BugReport bugReport = queryBugReportPort.queryBugReportById(bugReportId)
                .orElseThrow(() -> BugReportNotFoundException.EXCEPTION);

        return QueryBugReportDetailsResponse.builder()
                .title(bugReport.getTitle())
                .content(bugReport.getContent())
                .developmentArea(bugReport.getDevelopmentArea())
                .attachments(
                        bugReport.getBugAttachments().stream()
                                .map(BugAttachment::getAttachmentUrl)
                                .toList())
                .createdAt(bugReport.getCreatedAt())
                .build();
    }
}
