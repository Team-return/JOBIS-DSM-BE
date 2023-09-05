package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.TeacherQueryEmployCompaniesResponse;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryEmployCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public TeacherQueryEmployCompaniesResponse execute(
            String companyName,
            CompanyType type,
            Integer year,
            Long page
    ) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(companyName)
                .type(type)
                .year(year)
                .page(page)
                .limit(13)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryCompanyPort.getTotalCompanyCount(filter), filter.getLimit()
        );

        List<TeacherEmployCompaniesVO> companies = queryCompanyPort.queryEmployCompanies(filter);

        return new TeacherQueryEmployCompaniesResponse(companies, totalPageCount);
    }
}
