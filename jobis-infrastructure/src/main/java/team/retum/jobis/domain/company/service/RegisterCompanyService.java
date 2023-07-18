package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.company.persistence.CompanyAttachment;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyAlreadyExistsException;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.domain.company.presentation.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.user.persistence.User;
import com.example.jobisapplication.domain.auth.Authority;
import team.retum.jobis.domain.user.presentation.dto.response.TokenResponse;
import com.example.jobisapplication.common.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtTokenAdapter;
import team.retum.jobis.global.security.jwt.TokenType;
import com.example.jobisapplication.common.util.StringUtil;
import team.retum.jobis.thirdparty.api.FeignClientAdapter;

@RequiredArgsConstructor
@Service
public class RegisterCompanyService {

    private final FeignClientAdapter feignClientAdapter;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenAdapter jwtTokenAdapter;
    private final CodeFacade codeFacade;

    public TokenResponse execute(RegisterCompanyRequest request) {
        if (!feignClientAdapter.checkCompanyExists(request.getBusinessNumber())) {
            throw CompanyNotExistsException.EXCEPTION;
        }

        if (companyRepository.existsCompanyByBizNo(request.getBusinessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        User user = User.builder()
                .accountId(request.getBusinessNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.COMPANY)
                .build();

        String businessAreaKeyword = codeFacade.findCodeById(request.getBusinessAreaCode()).getKeyword();
        Company savedCompany = companyRepository.saveCompany(
                Company.builder()
                        .user(user)
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

        companyRepository.saveAllCompanyAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachment -> new CompanyAttachment(attachment, savedCompany))
                        .toList()
        );

        String accessToken = jwtTokenAdapter.generateAccessToken(user.getId(), user.getAuthority());
        String refreshToken = jwtTokenAdapter.generateRefreshToken(user.getId(), user.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.ACCESS))
                .refreshToken(refreshToken)
                .refreshExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.REFRESH))
                .authority(Authority.COMPANY)
                .build();
    }
}
