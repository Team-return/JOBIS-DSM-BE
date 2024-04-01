package team.retum.jobis.domain.bug.event;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.BugReport;

@Getter
@Builder
public class BugReportEvent {

    private final BugReport bugReport;
    private final String writer;
}
