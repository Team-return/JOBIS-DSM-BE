package team.retum.jobis.domain.bug.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;

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

}
