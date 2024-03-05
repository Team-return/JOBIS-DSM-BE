package team.retum.jobis.domain.notice.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.notice.persistence.entity.NoticeAttachmentEntity;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class QueryNoticeVO {

    private final Long id;
    private final String title;
    private final String content;
    private final List<NoticeAttachmentEntity> noticeAttachmentEntities;
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryNoticeVO(Long id, String title, String content, List<NoticeAttachmentEntity> noticeAttachmentEntities, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.noticeAttachmentEntities = noticeAttachmentEntities;
        this.createdAt = createdAt;
    }
}
