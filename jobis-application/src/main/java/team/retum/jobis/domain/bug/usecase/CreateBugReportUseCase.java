package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bug.dto.request.CreateBugReportRequest;
import team.retum.jobis.domain.bug.event.BugReportEvent;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.CommandBugReportPort;

@RequiredArgsConstructor
@UseCase
public class CreateBugReportUseCase {

    private final CommandBugReportPort commandBugReportPort;
    private final PublishEventPort publishEventPort;
    private final SecurityPort securityPort;

    public void execute(CreateBugReportRequest request) {
        BugReport savedBugReport = commandBugReportPort.save(
            BugReport.builder()
                .title(request.title())
                .content(request.content())
                .developmentArea(request.developmentArea())
                .studentId(securityPort.getCurrentUserId())
                .attachment(new BugAttachment(request.attachmentUrls()))
                .build()
        );

        String writer = securityPort.getCurrentStudent().getName();
        publishEventPort.publishEvent(
            BugReportEvent.builder()
                .bugReport(savedBugReport)
                .writer(writer)
                .build()
        );
    }
}
