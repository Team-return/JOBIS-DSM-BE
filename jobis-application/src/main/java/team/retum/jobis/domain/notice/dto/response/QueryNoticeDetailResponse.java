package team.retum.jobis.domain.notice.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QueryNoticeDetailResponse {

    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final List<NoticeAttachment> attachments;

    public static QueryNoticeDetailResponse from(Notice notice) {
        return QueryNoticeDetailResponse.builder()
            .title(notice.getTitle())
            .content(notice.getContent())
            .createdAt(notice.getCreatedAt())
            .attachments(notice.getAttachments())
            .build();
    }
}
