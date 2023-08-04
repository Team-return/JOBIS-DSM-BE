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
                .zipCode1(vo.getCompanyIntroduce())
                .address1(vo.getAddress1())
                .zipCode2(vo.getZipCode1())
                .address2(vo.getAddress2())
                .manager1(vo.getManager1())
                .phoneNumber1(vo.getPhoneNumber1())
                .manager2(vo.getManager2())
                .phoneNumber2(vo.getPhoneNumber2())
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
