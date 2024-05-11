package team.retum.jobis.domain.company.spi;

import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse.CompanyResponse;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QueryCompanyPort {

    List<StudentCompaniesVO> queryStudentCompanies(CompanyFilter filter);

    List<TeacherCompaniesVO> queryCompaniesByConditions(CompanyFilter filter);

    Long getTotalCompanyCount(CompanyFilter filter);

    Optional<CompanyDetailsVO> queryCompanyDetails(Long companyId);

    List<TeacherEmployCompaniesVO> queryEmployCompanies(CompanyFilter filter);

    Optional<Company> queryCompanyById(Long companyId);

    Optional<Company> queryCompanyByBusinessNumber(String businessNumber);

    List<Company> queryCompaniesByIdIn(List<Long> companyIds);

    boolean existsCompanyByBizNo(String bizNo);

    boolean existsCompanyById(Long companyId);

    List<CompanyResponse> queryReviewAvailableCompaniesByStudentId(Long studentId);

    Map<Long, String> queryCompanyNameByRecruitmentIds(List<Long> recruitmentIds);

    Long countCompanies();
}
