package team.retum.jobis.domain.company.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Company {

    private final Long id;

    private final String name;

    private final String bizNo;

    private final CompanyType type;

    private final boolean isMou;

    private final AddressInfo addressInfo;

    private final String representative;
    private final String representativePhoneNo;

    private final LocalDate foundedAt;

    private final double take;

    private final int workersCount;

    private final ManagerInfo managerInfo;

    private final String fax;

    private final String email;

    private final String companyIntroduce;

    private final String companyLogoUrl;

    private final String bizRegistrationUrl;

    private final String businessArea;

    private final String serviceName;

    private final List<String> attachmentUrls;

    public static Company of(RegisterCompanyRequest request, String businessArea) {
        return Company.builder()
                .companyIntroduce(request.companyIntroduce())
                .companyLogoUrl(request.companyProfileUrl())
                .bizRegistrationUrl(request.bizRegistrationUrl())
                .businessArea(businessArea)
                .serviceName(request.serviceName())
                .name(request.name())
                .type(CompanyType.PARTICIPATING)
                .take(request.take())
                .addressInfo(
                        AddressInfo.builder()
                                .mainAddress(request.mainAddress())
                                .mainAddressDetail(request.mainAddressDetail())
                                .mainZipCode(request.mainZipCode())
                                .subAddress(request.subAddress())
                                .subAddressDetail(request.subAddressDetail())
                                .subZipCode(request.subZipCode())
                                .build()
                )
                .managerInfo(
                        ManagerInfo.builder()
                                .managerName(request.managerName())
                                .managerPhoneNo(request.managerPhoneNo())
                                .subManagerName(request.subManagerName())
                                .subManagerPhoneNo(request.subManagerPhoneNo())
                                .build()
                )
                .workersCount(request.workerNumber())
                .email(request.email())
                .fax(request.fax())
                .isMou(false)
                .bizNo(request.businessNumber())
                .representative(request.representativeName())
                .representativePhoneNo(request.representativePhoneNo())
                .foundedAt(request.foundedAt())
                .attachmentUrls(request.attachmentUrls())
                .build();
    }

    public Company update(UpdateCompanyDetailsRequest request) {
        return this.toBuilder()
                .addressInfo(
                        AddressInfo.builder()
                                .mainAddress(request.mainAddress())
                                .mainAddressDetail(request.mainAddressDetail())
                                .mainZipCode(request.mainZipCode())
                                .subAddress(request.subAddress())
                                .subAddressDetail(request.subAddressDetail())
                                .subZipCode(request.subZipCode())
                                .build()
                )
                .take(request.take())
                .workersCount(request.workerNumber())
                .managerInfo(
                        ManagerInfo.builder()
                                .managerName(request.managerName())
                                .managerPhoneNo(request.managerPhoneNo())
                                .subManagerName(request.subManagerName())
                                .subManagerPhoneNo(request.subManagerPhoneNo())
                                .build()
                )
                .companyIntroduce(request.companyIntroduce())
                .representativePhoneNo(request.representativePhoneNo())
                .companyLogoUrl(request.companyProfileUrl())
                .fax(request.fax())
                .email(request.email())
                .serviceName(request.serviceName())
                .build();
    }

    public Company changeCompanyType(CompanyType type) {
        return this.toBuilder()
                .type(type)
                .build();
    }

    public Company convertToMou() {
        return this.toBuilder()
                .isMou(!this.isMou)
                .build();
    }

    public void verifySameCompany(Company currentCompany) {
        if (!this.getId().equals(currentCompany.getId())) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }
}
