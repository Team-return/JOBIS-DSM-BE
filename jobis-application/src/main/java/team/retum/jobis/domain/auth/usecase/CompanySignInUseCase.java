package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.exception.BadAuthCodeException;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

import java.util.Objects;

@RequiredArgsConstructor
@UseCase
public class CompanySignInUseCase {
    private final QueryCompanyPort queryCompanyPort;
    private final JwtPort jwtPort;
    private final SecurityPort securityPort;

    public TokenResponse execute(String businessRegistrationNumber, String authCode) {
        Company company = queryCompanyPort.queryCompanyByBusinessNumber(businessRegistrationNumber)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        checkServerAuthCode(authCode);

        return jwtPort.generateTokens(company.getId(), Authority.COMPANY, PlatformType.WEB);
    }

    private void checkServerAuthCode(String authCode) {
        if (!securityPort.getServerAuthCode().equals(authCode)) {
            throw BadAuthCodeException.EXCEPTION;
        }
    }
}
