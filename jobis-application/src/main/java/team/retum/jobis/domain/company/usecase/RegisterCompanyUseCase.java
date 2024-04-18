package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@UseCase
public class RegisterCompanyUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCodePort queryCodePort;
    private final JwtPort jwtPort;

    public TokenResponse execute(RegisterCompanyRequest request) {
        checkCompanyExists(request.businessNumber());
        checkCompanyRegistered(request.businessNumber());

        Code code = queryCodePort.getByIdOrThrow(request.businessAreaCode());

        Company savedCompany = commandCompanyPort.saveCompany(
            Company.of(request, code.getKeyword())
        );

        return jwtPort.generateTokens(savedCompany.getId(), Authority.COMPANY, PlatformType.WEB);
    }

    private void checkCompanyExists(String businessNumber) {
        if (!feignClientPort.checkCompanyExistsByBizNo(businessNumber)) {
            throw CompanyNotExistsException.EXCEPTION;
        }
    }

    private void checkCompanyRegistered(String businessNumber) {
        if (queryCompanyPort.existsCompanyByBizNo(businessNumber)) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }
    }
}
