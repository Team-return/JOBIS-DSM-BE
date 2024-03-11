package team.retum.jobis.domain.notice.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.persistence.entity.NoticeAttachmentEntity;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class QueryNoticeVO extends NoticeVO {

    @QueryProjection
    public QueryNoticeVO(Long id, String title, LocalDateTime createdAt) {
        super(id, title, createdAt);
    }
}
