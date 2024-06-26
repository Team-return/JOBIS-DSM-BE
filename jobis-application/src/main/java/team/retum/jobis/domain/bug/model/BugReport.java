package team.retum.jobis.domain.bug.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class BugReport {

    private final Long id;
    private final String title;
    private final String content;
    private final DevelopmentArea developmentArea;
    private final Long studentId;
    private final BugAttachment attachment;
    private final LocalDateTime createdAt;
}
