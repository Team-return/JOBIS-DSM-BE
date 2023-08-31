package team.retum.jobis.event.bug.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;

import java.util.List;

@Getter
@Builder
public class BugReportEvent {
    private final BugReport bugReport;
    private final List<BugAttachment> bugAttachments;
    private final String writer;
}
