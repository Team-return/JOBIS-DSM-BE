package com.example.jobisapplication.domain.company.spi;

import com.example.jobisapplication.domain.company.dto.CompanyFilter;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.company.spi.vo.CompanyDetailsVO;
import com.example.jobisapplication.domain.company.spi.vo.StudentCompaniesVO;
import com.example.jobisapplication.domain.company.spi.vo.TeacherCompaniesVO;
import com.example.jobisapplication.domain.company.spi.vo.TeacherEmployCompaniesVO;

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
}
