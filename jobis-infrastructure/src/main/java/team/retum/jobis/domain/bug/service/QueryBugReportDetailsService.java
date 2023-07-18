package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.persistence.BugAttachment;
import team.retum.jobis.domain.bug.persistence.BugReport;
import team.retum.jobis.domain.bug.persistence.repository.BugReportRepository;
import team.retum.jobis.domain.bug.exception.BugReportNotFoundException;
import team.retum.jobis.domain.bug.presentation.dto.response.QueryBugReportDetailsResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryBugReportDetailsService {

    private final BugReportRepository bugReportRepository;

    public QueryBugReportDetailsResponse execute(Long bugReportId) {
        BugReport bugReport = bugReportRepository.queryBugReportById(bugReportId)
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
