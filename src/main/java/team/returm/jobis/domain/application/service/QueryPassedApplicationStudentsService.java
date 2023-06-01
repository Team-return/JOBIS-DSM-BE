package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.response.QueryPassedApplicationStudentsResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

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
