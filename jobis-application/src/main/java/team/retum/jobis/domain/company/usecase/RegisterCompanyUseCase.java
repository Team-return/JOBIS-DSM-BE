package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

@RequiredArgsConstructor
@UseCase
public class RegisterCompanyUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCodePort queryCodePort;
    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(RegisterCompanyRequest request) {
        if (!feignClientPort.checkCompanyExistsByBizNo(request.businessNumber())) {
            throw CompanyNotExistsException.EXCEPTION;
        }

        if (queryCompanyPort.existsCompanyByBizNo(request.businessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        User savedUser = commandUserPort.saveUser(
                User.builder()
                        .accountId(request.businessNumber())
                        .password(securityPort.encodePassword(request.password()))
                        .authority(Authority.COMPANY)
                        .build()
        );

        Code code = queryCodePort.queryCodeById(request.businessAreaCode())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        commandCompanyPort.saveCompany(
                Company.of(request, savedUser.getId(), code.getKeyword())
        );

        return jwtPort.generateTokens(savedUser.getId(), Authority.COMPANY, PlatformType.WEB);
    }
}
