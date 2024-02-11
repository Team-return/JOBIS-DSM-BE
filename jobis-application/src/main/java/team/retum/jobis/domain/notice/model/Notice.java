package team.retum.jobis.domain.notice.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Notice {

    private final Long id;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final Long notificationId;

    private final List<NoticeAttachment> attachments;


}
