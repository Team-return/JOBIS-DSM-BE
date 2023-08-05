package team.retum.jobis.domain.bug.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Aggregate
public class BugReport {

    private final Long id;
    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final List<BugAttachment> bugAttachments;
    private final LocalDateTime createdAt;
}
