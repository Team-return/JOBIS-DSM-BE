package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bug.dto.CreateBugReportRequest;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.CommandBugReportPort;
import team.retum.jobis.domain.bug.spi.PublishBugReportPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateBugReportUseCase {

    private final CommandBugReportPort commandBugReportPort;
    private final PublishBugReportPort publishBugReportPort;
    private final SecurityPort securityPort;

    public void execute(CreateBugReportRequest request) {
        BugReport bugReport = commandBugReportPort.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .studentId(securityPort.getCurrentUserId())
                        .build()
        );

        List<BugAttachment> bugAttachments = commandBugReportPort.saveAllBugAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachmentUrl ->
                                BugAttachment.builder()
                                        .bugReportId(bugReport.getId())
                                        .attachmentUrl(attachmentUrl)
                                        .build()
                        ).toList()
        );

        publishBugReportPort.publishBugReport(bugReport, bugAttachments, securityPort.getCurrentStudent().getName());
    }
}
