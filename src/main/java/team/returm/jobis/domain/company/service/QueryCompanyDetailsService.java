package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.CompanyAttachment;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
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
    private final CompanyFacade companyFacade;
    private final RecruitmentRepository recruitmentRepository;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        Company company = companyRepository.queryCompanyDetails(companyId);
        Recruitment recruitment = recruitmentRepository.queryRecentRecruitmentByCompanyId(companyId);

        return QueryCompanyDetailsResponse
                .builder()
                .businessNumber(company.getBizNo())
                .companyProfileUrl(company.getCompanyLogoUrl())
                .companyIntroduce(company.getCompanyIntroduce())
                .zipCode1(company.getCompanyIntroduce())
                .address1(company.getAddress().getMainAddress())
                .zipCode2(company.getAddress().getMainZipCode())
                .address2(company.getAddress().getSubAddress())
                .manager1(company.getManager().getManagerName())
                .phoneNumber1(company.getManager().getManagerPhoneNo())
                .manager2(company.getManager().getSubManagerName())
                .phoneNumber2(company.getManager().getSubManagerPhoneNo())
                .fax(company.getFax())
                .email(company.getEmail())
                .representativeName(company.getRepresentative())
                .foundedAt(company.getFoundedAt())
                .workerNumber(company.getWorkersCount())
                .take(company.getSales())
                .recruitmentId(recruitment.getId())
                .attachments(getAttachmentUrls(company.getCompanyAttachments()))
                .build();
    }

    private List<String> getAttachmentUrls(List<CompanyAttachment> companyAttachments) {
        return companyAttachments.stream()
                .map(CompanyAttachment::getAttachmentUrl)
                .toList();
    }

}
