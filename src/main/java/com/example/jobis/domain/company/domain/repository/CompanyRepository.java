package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.domain.repository.vo.QStudentQueryCompaniesVO;
import com.example.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.jobis.domain.company.domain.QCompany.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<StudentQueryCompaniesVO> findCompanyInfoList() {
        return queryFactory
                .select(
                        new QStudentQueryCompaniesVO(
                             company.name,
                             company.companyLogoUrl,
                             company.sales
                        )
                )
                .from(company)
                .orderBy(company.name.desc())
                .fetch();
    }

    public Optional<Company> queryCompanyById(UUID companyId) {
        return companyJpaRepository.findById(companyId);
    }

    public Company findByBizNo(String bizNo) {
        return companyJpaRepository.findByBizNo(bizNo)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public boolean existsCompanyByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    public void saveCompany(Company company) {
        companyJpaRepository.save(company);
    }
}
