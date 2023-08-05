package team.retum.jobis.domain.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.AttachmentType;

import java.util.List;

@Getter
@Builder
public class CreateApplicationRequest {

    private List<AttachmentRequest> attachments;

    @Getter
    @Builder
    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;
    }
}
