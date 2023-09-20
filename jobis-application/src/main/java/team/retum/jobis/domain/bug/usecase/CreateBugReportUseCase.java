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
        List<BugAttachment> attachments = request.getAttachmentUrls().stream()
                .map(BugAttachment::of)
                .toList();

        BugReport savedBugReport = commandBugReportPort.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .studentId(securityPort.getCurrentUserId())
                        .attachments(attachments)
                        .build()
        );

        String writer = securityPort.getCurrentStudent().getName();
        publishBugReportEventPort.publishBugReport(savedBugReport, writer);
    }
}
