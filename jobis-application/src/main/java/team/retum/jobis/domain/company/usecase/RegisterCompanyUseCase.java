package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyAttachment;
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
    private final CommandUserPort commandUserPort;
    private final SecurityPort securityPort;
    private final QueryCodePort queryCodePort;
    private final JwtPort jwtPort;

    public TokenResponse execute(RegisterCompanyRequest request) {
        if (!feignClientPort.checkCompanyExistsByBizNo(request.getBusinessNumber())) {
            throw CompanyNotExistsException.EXCEPTION;
        }

        if (queryCompanyPort.existsCompanyByBizNo(request.getBusinessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        User userEntity = commandUserPort.saveUser(
                User.builder()
                        .accountId(request.getBusinessNumber())
                        .password(securityPort.encodePassword(request.getPassword()))
                        .authority(Authority.COMPANY)
                        .build()
        );

        Code code = queryCodePort.queryCodeById(request.getBusinessAreaCode())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        Company savedCompanyEntity = commandCompanyPort.saveCompany(
                Company.builder()
                        .id(userEntity.getId())
                        .companyIntroduce(request.getCompanyIntroduce())
                        .companyLogoUrl(request.getCompanyProfileUrl())
                        .bizRegistrationUrl(request.getBizRegistrationUrl())
                        .businessArea(code.getKeyword())
                        .serviceName(request.getServiceName())
                        .name(request.getName())
                        .take(request.getTake())
                        .mainAddress(request.getMainAddress())
                        .mainAddressDetail(request.getMainAddressDetail())
                        .mainZipCode(request.getMainZipCode())
                        .subAddress(request.getSubAddress())
                        .subAddressDetail(request.getSubAddressDetail())
                        .subZipCode(request.getSubZipCode())
                        .managerName(request.getManagerName())
                        .managerPhoneNo(request.getManagerPhoneNo())
                        .subManagerName(request.getSubManagerName())
                        .subManagerPhoneNo(request.getSubManagerPhoneNo())
                        .workersCount(request.getWorkerNumber())
                        .email(request.getEmail())
                        .fax(request.getFax())
                        .bizNo(request.getBusinessNumber())
                        .representative(request.getRepresentativeName())
                        .foundedAt(request.getFoundedAt())
                        .build()
        );

        commandCompanyPort.saveAllCompanyAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachment -> CompanyAttachment.builder()
                                .companyId(savedCompanyEntity.getId())
                                .attachmentUrl(attachment)
                                .build())
                        .toList()
        );

        return jwtPort.generateTokens(userEntity.getId(), userEntity.getAuthority());
    }
}
