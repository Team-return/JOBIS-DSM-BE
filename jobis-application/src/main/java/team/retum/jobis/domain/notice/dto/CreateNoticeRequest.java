package team.retum.jobis.domain.notice.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.AttachmentType;

import java.util.List;

@Getter
@Builder
public class CreateNoticeRequest {

    private String title;

    private String content;

    private List<AttachmentRequest> attachments;

    @Getter
    @Builder
    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;
    }
}
