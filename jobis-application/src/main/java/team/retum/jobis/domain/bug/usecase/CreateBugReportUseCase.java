package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.bug.dto.CreateBugReportRequest;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.CommandBugReportPort;

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
