package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class UpdateMouUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    public void execute(List<Long> companyIds) {
        List<Company> companies = queryCompanyPort.getCompaniesByIdIn(companyIds);
        if (companies.size() != companyIds.size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        commandCompanyPort.saveAllCompanies(
            companies.stream()
                .map(Company::convertToMou)
                .toList()
        );
    }
}
