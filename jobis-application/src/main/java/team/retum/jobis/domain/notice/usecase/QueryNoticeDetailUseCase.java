package team.retum.jobis.domain.notice.usecase;


import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.notice.dto.response.QueryNoticeDetailResponse;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticeDetailUseCase {

    private final QueryNoticePort queryNoticePort;

    public QueryNoticeDetailResponse execute(Long noticeId) {
        Notice notice = queryNoticePort.queryNoticeById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        return QueryNoticeDetailResponse.from(notice);

    }
}
