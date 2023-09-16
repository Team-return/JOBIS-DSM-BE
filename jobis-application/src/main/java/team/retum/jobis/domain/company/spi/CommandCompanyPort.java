package team.retum.jobis.domain.company.spi;

import team.retum.jobis.domain.company.model.Company;

import java.util.List;

public interface CommandCompanyPort {

    Company saveCompany(Company company);

    void saveAllCompanies(List<Company> companies);
}
