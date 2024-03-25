package team.retum.jobis.domain.notice.dto.request;

import lombok.Getter;
import team.retum.jobis.domain.notice.model.AttachmentType;

import java.util.List;

@Getter
public class CreateNoticeRequest {

    private final String title;

    private final String content;

    private final List<AttachmentRequest> attachments;

    public CreateNoticeRequest(String title, String content, List<AttachmentRequest> attachments) {
        this.title = title;
        this.content = content;
        this.attachments = attachments;
    }

    @Getter
    public static class AttachmentRequest {

        private final String url;

        private final AttachmentType type;

        public AttachmentRequest(String url, AttachmentType type) {
            this.url = url;
            this.type = type;
        }
    }
}
