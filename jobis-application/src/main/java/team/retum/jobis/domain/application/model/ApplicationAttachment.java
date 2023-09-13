package team.retum.jobis.domain.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@AllArgsConstructor
public class ApplicationAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;
}
