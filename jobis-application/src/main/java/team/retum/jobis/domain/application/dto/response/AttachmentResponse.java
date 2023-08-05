package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.AttachmentType;

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
