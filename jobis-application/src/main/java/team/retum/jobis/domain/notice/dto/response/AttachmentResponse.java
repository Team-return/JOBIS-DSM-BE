package team.retum.jobis.domain.notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.model.AttachmentType;
import team.retum.jobis.domain.notice.model.NoticeAttachment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {

    private String url;

    private AttachmentType type;

    public static AttachmentResponse of(NoticeAttachment noticeAttachment) {
        return new AttachmentResponse(
                noticeAttachment.getAttachmentUrl(),
                noticeAttachment.getType()
        );
    }
}
