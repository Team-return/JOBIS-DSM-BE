package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.company.dto.response.EmploymentRatesResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentRateUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public EmploymentRatesResponse execute() {
        List<EmploymentRatesResponse.ClassResponse> classResponses = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            List<CompanyVO> companies = queryCompanyPort.queryEmploymentRateByClassNumber(i);
            classResponses.add(new EmploymentRatesResponse.ClassResponse((long) i, companies));
        }

        return new EmploymentRatesResponse(classResponses);
    }
}
