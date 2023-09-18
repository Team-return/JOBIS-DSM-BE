package team.retum.jobis.domain.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;
}
