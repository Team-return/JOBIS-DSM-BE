package team.retum.jobis.domain.company.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.company.persistence.entity.CompanyAttachmentEntity;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import com.example.jobisapplication.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.persistence.repository.vo.QQueryCompanyDetailsVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QStudentQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QTeacherQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QTeacherQueryEmployCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QueryCompanyDetailsVO;
import team.retum.jobis.domain.company.persistence.repository.vo.StudentQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.TeacherQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.TeacherQueryEmployCompaniesVO;
import team.retum.jobis.domain.company.presentation.dto.CompanyFilter;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;

import java.util.List;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.acceptance.persistence.QAcceptance.acceptance;
import static team.retum.jobis.domain.application.persistence.QApplication.application;
import static team.retum.jobis.domain.company.persistence.QCompany.company;
import static team.retum.jobis.domain.company.persistence.QCompanyAttachment.companyAttachment;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitment.recruitment;

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
                                recruitment.id,
                                company.serviceName,
                                company.businessArea
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

    public Optional<CompanyEntity> queryCompanyById(Long companyId) {
        return companyJpaRepository.findById(companyId);
    }

    public boolean existsCompanyByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
        return companyJpaRepository.save(companyEntity);
    }

    public void saveAllCompanies(List<CompanyEntity> companies) {
        companyJpaRepository.saveAll(companies);
    }

    public void saveAllCompanyAttachment(List<CompanyAttachmentEntity> companyAttachmentEntities) {
        companyAttachmentJpaRepository.saveAll(companyAttachmentEntities);
    }

    public List<CompanyEntity> queryCompaniesByIdIn(List<Long> companyIds) {
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