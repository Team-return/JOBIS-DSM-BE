package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.dto.response.QueryMyRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyRecruitmentsUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public QueryMyRecruitmentsResponse execute() {
        Company company = securityPort.getCurrentCompany();

        List<MyAllRecruitmentsVO> recruitments = queryRecruitmentPort.getAllByCompanyId(company.getId());

        return new QueryMyRecruitmentsResponse(recruitments);
    }
}
