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
    private static final Integer FirstClass = 1;
    private static final Integer FourthClass = 4;

    public EmploymentRatesResponse execute() {
        List<EmploymentRatesResponse.ClassResponse> classResponses = new ArrayList<>();

        for (Integer classNum = FirstClass; classNum <= FourthClass; classNum++) {
            List<CompanyVO> companies = queryCompanyPort.queryEmploymentRateByClassNumber(classNum);
            classResponses.add(new EmploymentRatesResponse.ClassResponse(classNum, companies));
        }

        return new EmploymentRatesResponse(classResponses);
    }
}
