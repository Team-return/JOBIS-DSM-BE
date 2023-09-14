package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bug.dto.CreateBugReportRequest;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.CommandBugReportPort;
import team.retum.jobis.domain.bug.spi.PublishBugReportEventPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateBugReportUseCase {

    private final CommandBugReportPort commandBugReportPort;
    private final PublishBugReportEventPort publishBugReportEventPort;
    private final SecurityPort securityPort;

    public void execute(CreateBugReportRequest request) {
        BugReport savedBugReport = saveBugReport(request);
        String writer = securityPort.getCurrentStudent().getName();

        publishBugReportEventPort.publishBugReport(savedBugReport, writer);
    }

    private BugReport saveBugReport(CreateBugReportRequest request) {
        BugReport savedBugReport = commandBugReportPort.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .studentId(securityPort.getCurrentUserId())
                        .build()
        );

        if (request.getAttachmentUrls() != null) {
            List<BugAttachment> savedBugAttachments = commandBugReportPort.saveAllBugAttachment(
                    request.getAttachmentUrls().stream()
                            .map(attachmentUrl ->
                                    BugAttachment.builder()
                                            .bugReportId(savedBugReport.getId())
                                            .attachmentUrl(attachmentUrl)
                                            .build()
                            ).toList()
            );

            return savedBugReport.addAllBugAttachments(savedBugAttachments);
        }

        return savedBugReport;
    }
}
