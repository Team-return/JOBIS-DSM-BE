package team.retum.jobis.domain.application.dto.request;

import lombok.Builder;

@Builder
public record RejectionAttachmentRequest(
    String attachmentUrl
) {

}
