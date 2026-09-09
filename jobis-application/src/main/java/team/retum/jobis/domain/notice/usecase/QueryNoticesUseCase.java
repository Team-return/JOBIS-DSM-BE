package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.notice.dto.NoticeFilter;
import team.retum.jobis.domain.notice.dto.response.QueryNoticesResponse;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticesUseCase {

    private final QueryNoticePort queryNoticePort;

    public QueryNoticesResponse execute(Long page) {
        NoticeFilter filter = NoticeFilter.builder()
            .page(page)
            .limit(12)
            .build();

        List<NoticeVO> noticeVOs = queryNoticePort.getNotices(filter);

        return new QueryNoticesResponse(noticeVOs);
    }
}
