package team.retum.jobis.domain.notice.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class QueryNoticeDetailResponse {

    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final List<NoticeAttachment> attachments;
    private final Long viewCount;

    public static QueryNoticeDetailResponse of(Notice notice, Long viewCount) {
        return QueryNoticeDetailResponse.builder()
            .title(notice.getTitle())
            .content(notice.getContent())
            .createdAt(notice.getCreatedAt())
            .attachments(notice.getAttachments())
            .viewCount(viewCount)
            .build();
    }
}
