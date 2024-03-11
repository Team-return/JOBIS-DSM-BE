package team.retum.jobis.domain.notice.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.time.LocalDateTime;


@Getter
public class QueryNoticeVO extends NoticeVO {

    @QueryProjection
    public QueryNoticeVO(Long id, String title, LocalDateTime createdAt) {
        super(id, title, createdAt);
    }
}
