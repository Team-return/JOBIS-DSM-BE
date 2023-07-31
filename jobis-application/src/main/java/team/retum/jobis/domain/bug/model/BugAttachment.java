package team.retum.jobis.domain.bug.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class BugAttachment {

    private final Long bugReportId;

    private final String attachmentUrl;
}
