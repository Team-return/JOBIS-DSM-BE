package team.retum.jobis.domain.company.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.dto.request.UpdateMouRequest;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class UpdateConventionUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    public void execute(UpdateMouRequest request) {
        List<Company> companies = queryCompanyPort.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        commandCompanyPort.saveAllCompanies(
                companies.stream()
                        .map(Company::convertToMou)
                        .toList()
        );
    }
}
