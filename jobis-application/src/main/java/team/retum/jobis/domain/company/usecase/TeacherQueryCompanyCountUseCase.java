package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.TeacherQueryCompanyCountResponse;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryCompanyCountUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryCodePort queryCodePort;

    public TeacherQueryCompanyCountResponse execute(CompanyType type, String companyName, String region, Long businessArea, Long page) {
        CompanyFilter filter = CompanyFilter.builder()
                .type(type)
                .name(companyName)
                .region(region)
                .businessArea(
                        businessArea == null ? null :
                                queryCodePort.queryCodeById(businessArea)
                                        .orElseThrow(() -> CodeNotFoundException.EXCEPTION)
                                        .getKeyword()
                )
                .page(page)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryCompanyPort.getTotalCompanyCount(filter), filter.getLimit()
        );

        return new TeacherQueryCompanyCountResponse(totalPageCount);
    }
}
