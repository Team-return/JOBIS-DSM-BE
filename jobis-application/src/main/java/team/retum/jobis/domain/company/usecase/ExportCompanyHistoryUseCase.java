package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.WriteFilePort;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.ExportCompanyHistoryResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class ExportCompanyHistoryUseCase {

    private final WriteFilePort writeFilePort;
    private final QueryCompanyPort queryCompanyPort;

    public ExportCompanyHistoryResponse execute() {
        int year = LocalDate.now().getYear();

        CompanyFilter filter = CompanyFilter.builder()
            .build();

        List<TeacherCompaniesVO> companyList =
            queryCompanyPort.getByConditions(filter);

        return new ExportCompanyHistoryResponse(
            writeFilePort.writeCompanyExcelFile(companyList),
            getFileName(year)
        );
    }

    private String getFileName(Integer year) {
        return year + "_기업_엑셀_출력";
    }
}
