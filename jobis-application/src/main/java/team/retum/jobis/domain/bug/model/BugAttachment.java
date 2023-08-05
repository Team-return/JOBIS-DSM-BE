package team.retum.jobis.domain.bug.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class BugAttachment {

    private final Long bugReportId;

    private final String attachmentUrl;
}
