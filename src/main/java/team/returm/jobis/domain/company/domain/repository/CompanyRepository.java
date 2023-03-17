package team.returm.jobis.domain.company.domain.repository;

import team.returm.jobis.domain.company.domain.repository.vo.QStudentQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.returm.jobis.domain.company.domain.QCompany;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<StudentQueryCompaniesVO> findCompanyInfoList() {
        return queryFactory
                .select(
                        new QStudentQueryCompaniesVO(
                             QCompany.company.name,
                             QCompany.company.companyLogoUrl,
                             QCompany.company.sales
                        )
                )
                .from(QCompany.company)
                .orderBy(QCompany.company.name.desc())
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