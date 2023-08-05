package team.retum.jobis.domain.company.spi;

import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyAttachment;

import java.util.List;

public interface CommandCompanyPort {

    Company saveCompany(Company company);

    void saveAllCompanies(List<Company> companies);

    void saveAllCompanyAttachment(List<CompanyAttachment> companyAttachmentEntities);
}
