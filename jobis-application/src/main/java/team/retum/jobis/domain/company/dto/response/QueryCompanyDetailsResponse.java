package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class QueryCompanyDetailsResponse {

    private final String businessNumber;
    private final String companyName;
    private final String companyProfileUrl;
    private final String companyIntroduce;
    private final String mainAddress;
    private final String mainAddressDetail;
    private final String mainZipCode;
    private final String managerName;
    private final String managerPhoneNo;
    private final String email;
    private final String representativeName;
    private final String representativePhoneNo;
    private final LocalDate foundedAt;
    private final int workerNumber;
    private final double take;
    private final Long recruitmentId;
    private final List<String> attachments;
    private final String serviceName;
    private final Long businessAreaCode;
    private final String businessArea;
    private final String bizRegistrationUrl;
    private final boolean isHeadquarter;

    public static QueryCompanyDetailsResponse from(CompanyDetailsVO vo) {
        return QueryCompanyDetailsResponse.builder()
            .businessNumber(vo.getBusinessNumber())
            .companyName(vo.getCompanyName())
            .companyProfileUrl(vo.getCompanyProfileUrl())
            .companyIntroduce(vo.getCompanyIntroduce())
            .mainZipCode(vo.getMainZipCode())
            .mainAddress(vo.getMainAddress())
            .mainAddressDetail(vo.getMainAddressDetail())
            .managerName(vo.getManagerName())
            .managerPhoneNo(vo.getManagerPhoneNo())
            .email(vo.getEmail())
            .representativeName(vo.getRepresentativeName())
            .representativePhoneNo(vo.getRepresentativePhoneNo())
            .foundedAt(vo.getFoundedAt())
            .workerNumber(vo.getWorkerNumber())
            .take(vo.getTake())
            .recruitmentId(vo.getRecruitmentId())
            .attachments(vo.getAttachmentUrls())
            .serviceName(vo.getServiceName())
            .businessArea(vo.getBusinessArea())
            .bizRegistrationUrl(vo.getBizRegistrationUrl())
            .isHeadquarter(vo.isHeadquarter())
            .build();
    }
}
