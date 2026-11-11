package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.notice.dto.NoticeFilter;
import team.retum.jobis.domain.notice.dto.response.QueryNoticesResponse;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticesUseCase {

    private static final int PAGE_LIMIT = 12;

    private final QueryNoticePort queryNoticePort;

    public QueryNoticesResponse execute(Long page) {
        NoticeFilter filter = NoticeFilter.builder()
            .page(page)
            .limit(PAGE_LIMIT)
            .build();

        List<NoticeVO> noticeVOs = queryNoticePort.getNotices(filter);

        return new QueryNoticesResponse(noticeVOs);
    }

    public TotalPageCountResponse getTotalPageCount() {
        int totalPageCount = NumberUtil.getTotalPageCount(
            queryNoticePort.getNoticeCount(), PAGE_LIMIT
        );

        return new TotalPageCountResponse(totalPageCount);
    }
}
