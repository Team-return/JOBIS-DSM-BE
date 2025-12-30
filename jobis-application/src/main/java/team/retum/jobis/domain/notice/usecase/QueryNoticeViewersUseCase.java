package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.notice.dto.response.QueryNoticeViewersResponse;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;
import team.retum.jobis.domain.notice.spi.QueryViewLogPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticeViewersUseCase {

    private final QueryNoticePort queryNoticePort;
    private final QueryViewLogPort queryViewLogPort;

    public QueryNoticeViewersResponse execute(Long noticeId) {
        queryNoticePort.getById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        return QueryNoticeViewersResponse.of(
            queryViewLogPort.getViewersByNoticeId(noticeId)
        );
    }
}
