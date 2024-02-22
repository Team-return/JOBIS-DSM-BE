package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.dto.response.RegisterCompanyResponse;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.CommandCompanyPort;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@UseCase
public class RegisterCompanyUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCodePort queryCodePort;

    public RegisterCompanyResponse execute(RegisterCompanyRequest request) {
        if (!feignClientPort.checkCompanyExistsByBizNo(request.businessNumber())) {
            throw CompanyNotExistsException.EXCEPTION;
        }

        if (queryCompanyPort.existsCompanyByBizNo(request.businessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        Code code = queryCodePort.queryCodeById(request.businessAreaCode())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        Company company = commandCompanyPort.saveCompany(
                Company.builder()
                        .companyIntroduce(request.companyIntroduce())
                        .companyLogoUrl(request.companyProfileUrl())
                        .bizRegistrationUrl(request.bizRegistrationUrl())
                        .businessArea(code.getKeyword())
                        .serviceName(request.serviceName())
                        .name(request.name())
                        .type(CompanyType.PARTICIPATING)
                        .take(request.take())
                        .mainAddress(request.mainAddress())
                        .mainAddressDetail(request.mainAddressDetail())
                        .mainZipCode(request.mainZipCode())
                        .subAddress(request.subAddress())
                        .subAddressDetail(request.subAddressDetail())
                        .subZipCode(request.subZipCode())
                        .managerName(request.managerName())
                        .managerPhoneNo(request.managerPhoneNo())
                        .subManagerName(request.subManagerName())
                        .subManagerPhoneNo(request.subManagerPhoneNo())
                        .workersCount(request.workerNumber())
                        .email(request.email())
                        .fax(request.fax())
                        .isMou(false)
                        .bizNo(request.businessNumber())
                        .representative(request.representativeName())
                        .foundedAt(request.foundedAt())
                        .attachmentUrls(request.attachmentUrls())
                        .build()
        );

        return new RegisterCompanyResponse(company.getId());
    }
}
