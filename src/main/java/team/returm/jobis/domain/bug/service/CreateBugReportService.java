package team.returm.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.bug.domain.BugAttachment;
import team.returm.jobis.domain.bug.domain.BugReport;
import team.returm.jobis.domain.bug.domain.repository.BugRepository;
import team.returm.jobis.domain.bug.presentation.dto.request.CreateBugReportRequest;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateBugReportService {

    private final BugRepository bugRepository;

    public void execute(CreateBugReportRequest request) {
        BugReport bugReport = bugRepository.saveBugReport(
                BugReport.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .build()
        );

        bugRepository.saveAllBugAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachmentUrl ->
                                new BugAttachment(bugReport, attachmentUrl)
                        ).toList()
        );
    }
}
