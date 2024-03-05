package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.notice.dto.response.QueryNoticesResponse;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNoticesUseCase {
    
    private final QueryNoticePort queryNoticePort;

    public QueryNoticesResponse execute() {
        return new QueryNoticesResponse(
                queryNoticePort.queryNotices()
        );
    }
}
