package team.retum.jobis.domain.bug.dto;

import lombok.Builder;
import team.retum.jobis.domain.bug.model.DevelopmentArea;

import java.util.List;

@Builder
public record CreateBugReportRequest(
        String title,
        String content,
        DevelopmentArea developmentArea,
        List<String> attachmentUrls
) {
}