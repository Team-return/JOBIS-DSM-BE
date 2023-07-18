package team.retum.jobis.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.persistence.ApplicationAttachment;
import team.retum.jobis.domain.application.persistence.enums.AttachmentType;

@Getter
@AllArgsConstructor
public class AttachmentResponse {
    private String url;

    private AttachmentType type;

    public static AttachmentResponse of(ApplicationAttachment applicationAttachment) {
        return new AttachmentResponse(
                applicationAttachment.getAttachmentUrl(),
                applicationAttachment.getType()
        );
    }
}
