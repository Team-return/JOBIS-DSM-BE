package team.retum.jobis.domain.company.spi;

import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse.CompanyResponse;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

import java.util.List;
import java.util.Optional;

public interface QueryCompanyPort {

    List<StudentCompaniesVO> queryCompanyVoList(CompanyFilter filter);

    List<TeacherCompaniesVO> queryCompaniesByConditions(CompanyFilter filter);

    Long getTotalCompanyCount(CompanyFilter filter);

    CompanyDetailsVO queryCompanyDetails(Long companyId);

    List<TeacherEmployCompaniesVO> queryEmployCompanies(String name, CompanyType type, Integer year);

    List<String> queryCompanyAttachmentUrls(Long companyId);

    Optional<Company> queryCompanyById(Long companyId);

    List<Company> queryCompaniesByIdIn(List<Long> companyIds);

    boolean existsCompanyByBizNo(String bizNo);

    boolean existsCompanyById(Long companyId);

    List<CompanyResponse> queryReviewAvailableCompaniesByIds(Long studentId);
}
