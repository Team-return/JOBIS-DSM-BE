package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.dto.response.QueryPassedApplicationStudentsResponse;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryPassedApplicationStudentsUseCase {

    private final QueryApplicationPort queryApplicationPort;

    public QueryPassedApplicationStudentsResponse execute(Long companyId) {
        return new QueryPassedApplicationStudentsResponse(
            queryApplicationPort.queryPassedApplicationStudentsByCompanyId(companyId).stream()
                .map(QueryPassedApplicationStudentsResponse::from)
                .toList()
        );
    }
}
