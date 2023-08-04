package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyTypeRequest;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class UpdateCompanyTypeUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    public void execute(UpdateCompanyTypeRequest request) {

        List<Company> companies = queryCompanyPort.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        commandCompanyPort.saveAllCompanies(
                companies.stream()
                        .map(company -> company.changeCompanyType(request.getCompanyType()))
                        .toList()
        );
    }
}
