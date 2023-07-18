package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.persistence.repository.vo.QueryCompanyDetailsVO;
import team.retum.jobis.domain.company.presentation.dto.response.QueryCompanyDetailsResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyDetailsService {

    private final CompanyRepository companyRepository;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        QueryCompanyDetailsVO vo = companyRepository.queryCompanyDetails(companyId);
        List<String> attachmentUrls = companyRepository.queryCompanyAttachmentUrls(companyId);

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
