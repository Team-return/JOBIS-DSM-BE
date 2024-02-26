package team.retum.jobis.domain.notice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;

}
