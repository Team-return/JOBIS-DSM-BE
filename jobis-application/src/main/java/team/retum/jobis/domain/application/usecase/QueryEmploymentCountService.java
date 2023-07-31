package team.retum.jobis.domain.application.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.dto.response.QueryEmploymentCountResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentCountService {

    private final QueryApplicationPort queryApplicationPort;

    public QueryEmploymentCountResponse execute() {
        TotalApplicationCountVO counts = queryApplicationPort.queryTotalApplicationCount();

        return QueryEmploymentCountResponse.of(counts);
    }
}
