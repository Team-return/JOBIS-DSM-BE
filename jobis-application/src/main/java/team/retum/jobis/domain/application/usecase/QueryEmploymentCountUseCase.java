package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.dto.response.QueryEmploymentCountResponse;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentCountUseCase {

    private final QueryApplicationPort queryApplicationPort;

    public QueryEmploymentCountResponse execute() {
        TotalApplicationCountVO counts = queryApplicationPort.queryTotalApplicationCount();

        return QueryEmploymentCountResponse.of(counts);
    }
}
