package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.CompanyAttachment;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.domain.repository.vo.QueryCompanyDetailsVO;
import team.returm.jobis.domain.company.facade.CompanyFacade;
import team.returm.jobis.domain.company.presentation.dto.response.QueryCompanyDetailsResponse;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyDetailsService {

    private final CompanyRepository companyRepository;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        QueryCompanyDetailsVO vo = companyRepository.queryCompanyDetails(companyId);
        List<CompanyAttachment> companyAttachments = companyRepository.queryCompanyAttachments(companyId);

        return QueryCompanyDetailsResponse
                .builder()
                .businessNumber(vo.getBusinessNumber())
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
                .attachments(getAttachmentUrls(companyAttachments))
                .build();
    }

    private List<String> getAttachmentUrls(List<CompanyAttachment> companyAttachments) {
        return companyAttachments.stream()
                .map(CompanyAttachment::getAttachmentUrl)
                .toList();
    }

}
