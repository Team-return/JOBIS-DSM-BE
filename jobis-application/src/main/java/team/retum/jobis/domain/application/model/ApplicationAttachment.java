package team.retum.jobis.domain.application.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class ApplicationAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;

    private final Long applicationId;
}
