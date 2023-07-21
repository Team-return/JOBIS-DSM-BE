package com.example.jobisapplication.domain.bug.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.bug.dto.CreateBugReportRequest;
import com.example.jobisapplication.domain.bug.model.BugAttachment;
import com.example.jobisapplication.domain.bug.model.BugReport;
import com.example.jobisapplication.domain.bug.spi.CommandBugReportPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class CreateBugReportUseCase {

    private final CommandBugReportPort commandBugReportPort;

    public void execute(CreateBugReportRequest request) {
        BugReport bugReport = commandBugReportPort.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .build()
        );

        commandBugReportPort.saveAllBugAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachmentUrl ->
                                BugAttachment.builder()
                                        .bugReportId(bugReport.getId())
                                        .attachmentUrl(attachmentUrl)
                                        .build()
                        ).toList()
        );
    }
}
