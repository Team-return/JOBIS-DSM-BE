package team.retum.jobis.domain.bug.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.model.DevelopmentArea;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QueryBugReportDetailsResponse {

    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final List<String> attachments;
    private final LocalDateTime createdAt;

    public static QueryBugReportDetailsResponse from(BugReport bugReport) {
        return QueryBugReportDetailsResponse.builder()
            .title(bugReport.getTitle())
            .content(bugReport.getContent())
            .developmentArea(bugReport.getDevelopmentArea())
            .attachments(bugReport.getAttachment().attachmentUrls())
            .createdAt(bugReport.getCreatedAt())
            .build();
    }
}
