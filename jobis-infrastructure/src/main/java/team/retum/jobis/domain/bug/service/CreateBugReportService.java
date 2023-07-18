package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.domain.BugAttachment;
import team.retum.jobis.domain.bug.domain.BugReport;
import team.retum.jobis.domain.bug.domain.repository.BugReportRepository;
import team.retum.jobis.domain.bug.presentation.dto.request.CreateBugReportRequest;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateBugReportService {

    private final BugReportRepository bugReportRepository;

    public void execute(CreateBugReportRequest request) {
        BugReport bugReport = bugReportRepository.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .build()
        );

        bugReportRepository.saveAllBugAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachmentUrl ->
                                new BugAttachment(bugReport, attachmentUrl)
                        ).toList()
        );
    }
}
