package team.retum.jobis.domain.company.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.domain.company.dto.request.TeacherRegisterCompanyRequest;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private final String email;

    private final String companyIntroduce;

    private final String companyLogoUrl;

    private final String bizRegistrationUrl;

    private final String businessArea;

    private final String serviceName;

    private final List<String> attachmentUrls;

    private final boolean headquarter;

    public static Company of(Long id, RegisterCompanyRequest request, String businessArea) {
        return Company.builder()
            .id(id)
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
                    .build()
            )
            .managerInfo(
                ManagerInfo.builder()
                    .managerName(request.managerName())
                    .managerPhoneNo(request.managerPhoneNo())
                    .build()
            )
            .workersCount(request.workerNumber())
            .email(request.email())
            .isMou(false)
            .bizNo(request.businessNumber())
            .representative(request.representativeName())
            .representativePhoneNo(request.representativePhoneNo())
            .foundedAt(request.foundedAt())
            .attachmentUrls(request.attachmentUrls())
            .headquarter(request.headquarter())
            .build();
    }

    public static Company of(Long id, TeacherRegisterCompanyRequest request, String businessArea) {
        return Company.builder()
                .id(id)
                .companyIntroduce("수동 등록된 기업입니다.")
                .companyLogoUrl(request.companyProfileUrl())
                .bizRegistrationUrl("N/A")
                .businessArea(businessArea)
                .serviceName("N/A")
                .name(request.name())
                .type(CompanyType.MANUAL_ADD)
                .take(12)
                .addressInfo(
                        AddressInfo.builder()
                                .mainAddress("대전광역시 유성구 가정북로 76")
                                .mainAddressDetail("N/A")
                                .mainZipCode("34111")
                                .build()
                )
                .managerInfo(
                        ManagerInfo.builder()
                                .managerName("N/A")
                                .managerPhoneNo("N/A")
                                .build()
                )
                .workersCount(0)
                .email("N/A")
                .isMou(false)
                .bizNo(request.businessNumber())
                .representative("N/A")
                .representativePhoneNo("N/A")
                .foundedAt(LocalDate.from(LocalDateTime.now()))
                .attachmentUrls(List.of("N/A"))
                .headquarter(true)
                .build();
    }

    public Company update(UpdateCompanyDetailsRequest request) {
        return this.toBuilder()
            .addressInfo(
                AddressInfo.builder()
                    .mainAddress(request.mainAddress())
                    .mainAddressDetail(request.mainAddressDetail())
                    .mainZipCode(request.mainZipCode())
                    .build()
            )
            .take(request.take())
            .workersCount(request.workerNumber())
            .managerInfo(
                ManagerInfo.builder()
                    .managerName(request.managerName())
                    .managerPhoneNo(request.managerPhoneNo())
                    .build()
            )
            .representativePhoneNo(request.representativePhoneNo())
            .companyIntroduce(request.companyIntroduce())
            .companyLogoUrl(request.companyProfileUrl())
            .email(request.email())
            .serviceName(request.serviceName())
            .attachmentUrls(request.attachmentUrls())
            .headquarter(request.headquarter())
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
