package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;

@RequiredArgsConstructor
@UseCase
public class UpdateCompanyDetailsUseCase {

    private final SecurityPort securityPort;
    private final CommandCompanyPort commandCompanyPort;

    public void execute(UpdateCompanyDetailsRequest request) {
        Company company = securityPort.getCurrentCompany();

        commandCompanyPort.saveCompany(
                company.update(
                        request.getMainAddress(), request.getMainAddressDetail(), request.getMainZipCode(),
                        request.getSubAddress(), request.getSubAddressDetail(), request.getSubZipCode(),
                        request.getTake(), request.getWorkerNumber(),
                        request.getManagerName(), request.getManagerPhoneNo(),
                        request.getSubManagerName(), request.getSubManagerPhoneNo(),
                        request.getCompanyIntroduce(), request.getCompanyProfileUrl(),
                        request.getFax(), request.getEmail()
                )
        );
    }
}