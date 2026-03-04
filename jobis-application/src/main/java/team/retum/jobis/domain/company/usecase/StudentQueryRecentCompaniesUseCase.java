package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.CachePort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.response.StudentQueryRecentCompaniesResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryRecentCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final CachePort cachePort;
    private final SecurityPort securityPort;

    public StudentQueryRecentCompaniesResponse execute() {
        Long studentId = securityPort.getCurrentUserId();

        List<Long> companyIds = cachePort.getRecentCompanyId(studentId);

        System.out.println(companyIds);
        return new StudentQueryRecentCompaniesResponse(
            queryCompanyPort.getRecentCompanies(companyIds)
        );
    }
}
