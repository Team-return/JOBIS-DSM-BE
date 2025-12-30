package team.retum.jobis.domain.notice.usecase;


import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.notice.dto.response.QueryNoticeDetailResponse;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;
import team.retum.jobis.domain.notice.spi.QueryViewLogPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticeDetailUseCase {

    private final QueryNoticePort queryNoticePort;
    private final QueryViewLogPort queryViewLogPort;

    public QueryNoticeDetailResponse execute(Long noticeId) {
        Notice notice = queryNoticePort.getById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        Long viewCount = queryViewLogPort.countByNoticeId(noticeId);

        return QueryNoticeDetailResponse.of(notice, viewCount);

    }
}
