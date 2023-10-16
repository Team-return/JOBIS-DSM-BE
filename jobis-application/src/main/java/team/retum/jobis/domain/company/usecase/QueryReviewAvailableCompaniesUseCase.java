package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryReviewAvailableCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final SecurityPort securityPort;

    public QueryReviewAvailableCompaniesResponse execute() {
        Student currentStudent = securityPort.getCurrentStudent();
        return new QueryReviewAvailableCompaniesResponse(
                queryCompanyPort.queryReviewAvailableCompaniesByStudentInfo(
                        currentStudent.getId(), currentStudent.getName()
                )
        );
    }
}
