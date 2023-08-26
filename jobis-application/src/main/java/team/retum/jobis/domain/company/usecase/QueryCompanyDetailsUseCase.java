package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.company.dto.response.QueryCompanyDetailsResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCompanyDetailsUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        CompanyDetailsVO vo = queryCompanyPort.queryCompanyDetails(companyId);
        List<String> attachmentUrls = queryCompanyPort.queryCompanyAttachmentUrls(companyId);

        return QueryCompanyDetailsResponse
                .builder()
                .businessNumber(vo.getBusinessNumber())
                .companyName(vo.getCompanyName())
                .companyProfileUrl(vo.getCompanyProfileUrl())
                .companyIntroduce(vo.getCompanyIntroduce())
                .mainZipCode(vo.getMainZipCode())
                .mainAddress(vo.getMainAddress())
                .mainAddressDetail(vo.getMainAddressDetail())
                .subZipCode(vo.getSubZipCode())
                .subAddress(vo.getSubAddress())
                .subAddressDetail(vo.getSubAddressDetail())
                .managerName(vo.getManagerName())
                .managerPhoneNo(vo.getManagerPhoneNo())
                .subManagerName(vo.getSubManagerName())
                .subManagerPhoneNo(vo.getSubManagerPhoneNo())
                .fax(vo.getFax())
                .email(vo.getEmail())
                .representativeName(vo.getRepresentativeName())
                .foundedAt(vo.getFoundedAt())
                .workerNumber(vo.getWorkerNumber())
                .take(vo.getTake())
                .recruitmentId(vo.getRecruitmentId())
                .attachments(attachmentUrls)
                .serviceName(vo.getServiceName())
                .businessArea(vo.getBusinessArea())
                .build();
    }
}