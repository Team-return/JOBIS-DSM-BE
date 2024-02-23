package team.retum.jobis.domain.notice.dto.request;

import lombok.Getter;
import team.retum.jobis.domain.notice.model.AttachmentType;

import java.util.List;

@Getter
public class CreateNoticeRequest {

    private String title;

    private String content;

    private List<AttachmentRequest> attachments;

    public CreateNoticeRequest(String title, String content, List<AttachmentRequest> attachments) {
        this.title = title;
        this.content = content;
        this.attachments = attachments;
    }

    @Getter
    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;

        public AttachmentRequest(String url, AttachmentType type) {
            this.url = url;
            this.type = type;
        }
    }
}
