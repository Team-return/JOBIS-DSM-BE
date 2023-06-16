package team.returm.jobis.domain.company.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.CompanyAttachment;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.company.domain.repository.vo.QQueryCompanyDetailsVO;
import team.returm.jobis.domain.company.domain.repository.vo.QStudentQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.QTeacherQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.QTeacherQueryEmployCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.QueryCompanyDetailsVO;
import team.returm.jobis.domain.company.domain.repository.vo.StudentQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.TeacherQueryCompaniesVO;
import team.returm.jobis.domain.company.domain.repository.vo.TeacherQueryEmployCompaniesVO;
import team.returm.jobis.domain.company.presentation.dto.CompanyFilter;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;

import java.util.List;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static team.returm.jobis.domain.acceptance.domain.QAcceptance.acceptance;
import static team.returm.jobis.domain.application.domain.QApplication.application;
import static team.returm.jobis.domain.company.domain.QCompany.company;
import static team.returm.jobis.domain.company.domain.QCompanyAttachment.companyAttachment;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyAttachmentJpaRepository companyAttachmentJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<StudentQueryCompaniesVO> queryCompanyVoList(CompanyFilter filter) {
        return queryFactory
                .select(
                        new QStudentQueryCompaniesVO(
                                company.id,
                                company.name,
                                company.companyLogoUrl,
                                company.take,
                                recruitment.count()
                        )
                )
                .from(company)
                .leftJoin(recruitment)
                .on(recentRecruitment(RecruitStatus.RECRUITING))
                .where(containsName(filter.getName()))
                .orderBy(company.name.desc())
                .groupBy(company.id)
                .offset(filter.getOffset())
                .limit(11)
                .fetch();
    }

    public List<TeacherQueryCompaniesVO> queryCompaniesByConditions(CompanyFilter filter) {
        return queryFactory
                .select(
                        new QTeacherQueryCompaniesVO(
                                company.id,
                                company.name,
                                company.address.mainAddress,
                                company.businessArea,
                                company.workersCount,
                                company.take,
                                company.type,
                                company.isMou,
                                recruitment.personalContact,
                                recruitment.recruitYear,
                                acceptancesCount()
                        )
                ).from(company)
                .leftJoin(recruitment)
                .on(recentRecruitment(null))
                .where(
                        eqCompanyType(filter.getType()),
                        containsName(filter.getName()),
                        eqRegion(filter.getRegion()),
                        eqBusinessArea(filter.getBusinessArea())
                )
                .orderBy(company.name.desc())
                .offset(filter.getOffset())
                .limit(11)
                .fetch();
    }

    public Long getTotalCompanyCount(CompanyFilter filter) {
        return queryFactory
                .select(company.count())
                .from(company)
                .where(
                        eqCompanyType(filter.getType()),
                        containsName(filter.getName()),
                        eqRegion(filter.getRegion()),
                        eqBusinessArea(filter.getBusinessArea())
                ).fetchOne();
    }

    public JPQLQuery<Long> acceptancesCount() {
        return select(acceptance.count())
                .from(acceptance)
                .where(acceptance.company.eq(company));
    }

    public QueryCompanyDetailsVO queryCompanyDetails(Long companyId) {
        return queryFactory
                .select(
                        new QQueryCompanyDetailsVO(
                                company.bizNo,
                                company.name,
                                company.companyLogoUrl,
                                company.companyIntroduce,
                                company.address.mainZipCode,
                                company.address.mainAddress,
                                company.address.subZipCode,
                                company.address.subAddress,
                                company.manager.managerName,
                                company.manager.managerPhoneNo,
                                company.manager.subManagerName,
                                company.manager.subManagerPhoneNo,
                                company.fax,
                                company.email,
                                company.representative,
                                company.foundedAt,
                                company.workersCount,
                                company.take,
                                recruitment.id
                        )
                )
                .from(company)
                .leftJoin(recruitment)
                .on(
                        recruitment.company.id.eq(company.id),
                        recentRecruitment(RecruitStatus.RECRUITING)
                )
                .where(company.id.eq(companyId))
                .fetchOne();
    }

    public List<TeacherQueryEmployCompaniesVO> queryEmployCompanies(String name, CompanyType type, Integer year) {
        return queryFactory
                .select(
                        new QTeacherQueryEmployCompaniesVO(
                                company.id,
                                company.name,
                                application.countDistinct(),
                                acceptance.countDistinct()
                        )
                )
                .from(company)
                .leftJoin(company.acceptances, acceptance)
                .leftJoin(company.recruitmentList, recruitment)
                .on(
                        recruitment.company.id.eq(company.id),
                        recentRecruitment(null)
                )
                .leftJoin(recruitment.applications, application)
                .on(application.applicationStatus.eq(ApplicationStatus.FIELD_TRAIN))
                .where(
                        containsName(name),
                        eqCompanyType(type),
                        eqYear(year)
                )
                .orderBy(company.name.asc())
                .groupBy(company.id, company.name)
                .fetch();
    }

    public List<String> queryCompanyAttachmentUrls(Long companyId) {
        return queryFactory
                .select(companyAttachment.attachmentUrl)
                .from(companyAttachment)
                .where(companyAttachment.company.id.eq(companyId))
                .fetch();
    }

    public Optional<Company> queryCompanyById(Long companyId) {
        return companyJpaRepository.findById(companyId);
    }

    public boolean existsCompanyByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    public Company saveCompany(Company company) {
        return companyJpaRepository.save(company);
    }

    public void saveAllCompanies(List<Company> companies) {
        companyJpaRepository.saveAll(companies);
    }

    public void saveAllCompanyAttachment(List<CompanyAttachment> companyAttachments) {
        companyAttachmentJpaRepository.saveAll(companyAttachments);
    }

    public List<Company> queryCompaniesByIdIn(List<Long> companyIds) {
        return companyJpaRepository.findAllByIdIn(companyIds);
    }

    public boolean existsCompanyById(Long companyId) {
        return companyJpaRepository.existsById(companyId);
    }

    //==conditions==//

    private BooleanExpression containsName(String name) {
        return name == null ? null : company.name.contains(name);
    }

    private BooleanExpression eqCompanyType(CompanyType type) {
        return type == null ? null : company.type.eq(type);
    }

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitment.recruitYear.eq(year);
    }

    private BooleanExpression recentRecruitment(RecruitStatus status) {
        return recruitment.createdAt.eq(
                JPAExpressions.select(recruitment.createdAt.max())
                        .from(recruitment)
                        .where(
                                recruitment.company.id.eq(company.id),
                                eqRecruitmentStatus(status)
                        )
        );
    }

    private BooleanExpression eqRecruitmentStatus(RecruitStatus status) {
        return status == null ? null : recruitment.status.eq(status);
    }


    private BooleanExpression eqRegion(String region) {
        return region == null ? null : company.address.mainAddress.startsWith(region);
    }

    private BooleanExpression eqBusinessArea(String businessArea) {
        return businessArea == null ? null : company.businessArea.eq(businessArea);
    }
}
