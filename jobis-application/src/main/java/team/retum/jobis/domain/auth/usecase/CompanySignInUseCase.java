package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@UseCase
public class CompanySignInUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final JwtPort jwtPort;
    private final SecurityPort securityPort;

    public TokenResponse execute(String businessRegistrationNumber) {
        Company company = queryCompanyPort.getByBusinessNumber(businessRegistrationNumber)
            .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return jwtPort.generateTokens(company.getId(), Authority.COMPANY, PlatformType.WEB);
    }
}
