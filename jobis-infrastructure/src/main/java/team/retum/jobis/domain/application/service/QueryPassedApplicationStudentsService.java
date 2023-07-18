package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.presentation.dto.response.QueryPassedApplicationStudentsResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryPassedApplicationStudentsService {

    private final ApplicationRepository applicationRepository;

    public QueryPassedApplicationStudentsResponse execute(Long companyId) {
        return new QueryPassedApplicationStudentsResponse(
                applicationRepository.queryPassedApplicationStudentsByCompanyId(companyId).stream()
                        .map(QueryPassedApplicationStudentsResponse::of)
                        .toList()
        );
    }
}
