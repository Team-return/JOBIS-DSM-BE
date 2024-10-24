package team.retum.jobis.domain.company.spi;

import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse.CompanyResponse;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QueryCompanyPort {

    List<StudentCompaniesVO> getStudentCompanies(CompanyFilter filter);

    List<TeacherCompaniesVO> getByConditions(CompanyFilter filter);

    Long getTotalCompanyCount(CompanyFilter filter);

    Optional<CompanyDetailsVO> getCompanyDetails(Long companyId);

    List<TeacherEmployCompaniesVO> getEmployCompanies(CompanyFilter filter);

    Optional<Company> getById(Long companyId);

    Optional<Company> getByBusinessNumber(String businessNumber);

    List<Company> getByIdIn(List<Long> companyIds);

    boolean existsByBizNo(String bizNo);

    boolean existsById(Long companyId);

    List<CompanyResponse> getReviewAvailableCompaniesByStudentId(Long studentId);

    Map<Long, String> getCompanyNameByRecruitmentIds(List<Long> recruitmentIds);

    List<CompanyVO> getEmploymentRateByClassNumber(Integer classNum);

}
