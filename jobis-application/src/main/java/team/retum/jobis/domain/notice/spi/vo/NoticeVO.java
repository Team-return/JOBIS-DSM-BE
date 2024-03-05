package team.retum.jobis.domain.notice.spi.vo;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.NoticeAttachment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class NoticeVO {

    private final Long id;
    private final String title;
    private final String content;
    private final List<NoticeAttachment> noticeAttachments;
    private final LocalDateTime createdAt;
}