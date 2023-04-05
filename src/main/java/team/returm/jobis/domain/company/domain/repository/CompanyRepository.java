package team.returm.jobis.domain.company.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.repository.vo.QStudentQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;

import static team.returm.jobis.domain.company.domain.QCompany.company;
import static team.returm.jobis.domain.company.domain.QCompanyAttachment.companyAttachment;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<StudentQueryCompaniesVO> queryCompanyVoList() {
        return queryFactory
                .select(
                        new QStudentQueryCompaniesVO(
                                company.id,
                                company.name,
                                company.companyLogoUrl,
                                company.sales
                        )
                )
                .from(company)
                .orderBy(company.name.desc())
                .fetch();
    }

    public Company queryCompanyDetails(Long companyId) {
        return queryFactory
                .selectFrom(company)
                .leftJoin(company.companyAttachments, companyAttachment).fetchJoin()
                .where(company.id.eq(companyId))
                .fetchOne();
    }

    public Optional<Company> queryCompanyById(Long companyId) {
        return companyJpaRepository.findById(companyId);
    }

    public boolean existsCompanyByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    public void saveCompany(Company company) {
        companyJpaRepository.save(company);
    }
}
