package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.FeignClientPort;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.common.util.StringUtil;
import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.Authority;
import com.example.jobisapplication.domain.auth.spi.JwtPort;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.company.dto.request.RegisterCompanyRequest;
import com.example.jobisapplication.domain.company.exception.CompanyAlreadyExistsException;
import com.example.jobisapplication.domain.company.exception.CompanyNotExistsException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.model.CompanyAttachment;
import com.example.jobisapplication.domain.company.spi.CommandCompanyPort;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import com.example.jobisapplication.domain.user.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class RegisterCompanyUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CommandCompanyPort commandCompanyPort;
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

        User userEntity = User.builder()
                .accountId(request.getBusinessNumber())
                .password(securityPort.encodePassword(request.getPassword()))
                .authority(Authority.COMPANY)
                .build();

        String businessAreaKeyword = queryCodePort.queryCodeById(request.getBusinessAreaCode()).getKeyword();
        Company savedCompanyEntity = commandCompanyPort.saveCompany(
                Company.builder()
                        .id(userEntity.getId())
                        .companyIntroduce(request.getCompanyIntroduce())
                        .companyLogoUrl(request.getCompanyProfileUrl())
                        .bizRegistrationUrl(request.getBizRegistrationUrl())
                        .businessArea(businessAreaKeyword)
                        .serviceName(request.getServiceName())
                        .name(request.getName())
                        .take(request.getTake())
                        .mainAddress(StringUtil.mergeString(request.getMainAddress(), request.getMainAddressDetail()))
                        .mainZipCode(request.getMainZipCode())
                        .subAddress(StringUtil.mergeString(request.getSubAddress(), request.getSubAddressDetail()))
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
