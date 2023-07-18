package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryTotalApplicationCountVO;
import team.retum.jobis.domain.application.presentation.dto.response.QueryEmploymentCountResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryEmploymentCountService {

    private final ApplicationRepository applicationRepository;

    public QueryEmploymentCountResponse execute() {
        QueryTotalApplicationCountVO counts = applicationRepository.queryTotalApplicationCount();

        return QueryEmploymentCountResponse.of(counts);
    }
}
