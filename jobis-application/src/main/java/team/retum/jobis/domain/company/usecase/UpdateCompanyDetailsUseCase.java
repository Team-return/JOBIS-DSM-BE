package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@UseCase
public class UpdateCompanyDetailsUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
    private final SecurityPort securityPort;

    public void execute(UpdateCompanyDetailsRequest request, Long companyId) {
        Company company = queryCompanyPort.queryCompanyById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        if (securityPort.getCurrentUserAuthority() == Authority.COMPANY) {
            company.verifySameCompany(securityPort.getCurrentCompany());
        }

        commandCompanyPort.saveCompany(
                company.update(
                        request.getMainAddress(), request.getMainAddressDetail(), request.getMainZipCode(),
                        request.getSubAddress(), request.getSubAddressDetail(), request.getSubZipCode(),
                        request.getTake(), request.getWorkerNumber(),
                        request.getManagerName(), request.getManagerPhoneNo(),
                        request.getSubManagerName(), request.getSubManagerPhoneNo(),
                        request.getCompanyIntroduce(), request.getCompanyProfileUrl(),
                        request.getFax(), request.getEmail(), request.getServiceName()
                )
        );
    }
}
