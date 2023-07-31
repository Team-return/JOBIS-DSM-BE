package team.retum.jobis.domain.company.persistence;

import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyAttachment;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.CompanyPort;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.company.persistence.mapper.CompanyAttachmentMapper;
import team.retum.jobis.domain.company.persistence.mapper.CompanyMapper;
import team.retum.jobis.domain.company.persistence.repository.CompanyAttachmentJpaRepository;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.company.persistence.repository.vo.*;

import java.util.List;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.acceptance.persistence.entity.QAcceptanceEntity.acceptanceEntity;
import static team.retum.jobis.domain.application.persistence.entity.QApplicationEntity.applicationEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyAttachmentEntity.companyAttachmentEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;

@Repository
@RequiredArgsConstructor
public class CompanyPersistenceAdapter implements CompanyPort {

    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyMapper companyMapper;
    private final CompanyAttachmentJpaRepository companyAttachmentJpaRepository;
    private final CompanyAttachmentMapper companyAttachmentMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Company saveCompany(Company company) {
        return companyMapper.toDomain(
                companyJpaRepository.save(
                        companyMapper.toEntity(company)
                )
        );
    }

    @Override
    public void saveAllCompanies(List<Company> companies) {
        companyJpaRepository.saveAll(
                companies.stream()
                        .map(companyMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public void saveAllCompanyAttachment(List<CompanyAttachment> companyAttachments) {
        companyAttachmentJpaRepository.saveAll(
                companyAttachments.stream()
                        .map(companyAttachmentMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public List<StudentCompaniesVO> queryCompanyVoList(CompanyFilter filter) {
        return queryFactory
                .select(
                        new QStudentQueryCompaniesVO(
                                companyEntity.id,
                                companyEntity.name,
                                companyEntity.companyLogoUrl,
                                companyEntity.take,
                                recruitmentEntity.count()
                        )
                )
                .from(companyEntity)
                .leftJoin(recruitmentEntity)
                .on(recentRecruitment(RecruitStatus.RECRUITING))
                .where(containsName(filter.getName()))
                .orderBy(companyEntity.name.desc())
                .groupBy(companyEntity.id)
                .offset(filter.getOffset())
                .limit(11)
                .fetch().stream()
                .map(studentQueryCompaniesVO -> (StudentCompaniesVO) studentQueryCompaniesVO).toList();
    }

    @Override
    public List<TeacherCompaniesVO> queryCompaniesByConditions(CompanyFilter filter) {
        return queryFactory
                .select(
                        new QTeacherQueryCompaniesVO(
                                companyEntity.id,
                                companyEntity.name,
                                companyEntity.address.mainAddress,
                                companyEntity.businessArea,
                                companyEntity.workersCount,
                                companyEntity.take,
                                companyEntity.type,
                                companyEntity.isMou,
                                recruitmentEntity.personalContact,
                                recruitmentEntity.recruitYear,
                                acceptancesCount()
                        )
                ).from(companyEntity)
                .leftJoin(recruitmentEntity)
                .on(recentRecruitment(null))
                .where(
                        eqCompanyType(filter.getType()),
                        containsName(filter.getName()),
                        eqRegion(filter.getRegion()),
                        eqBusinessArea(filter.getBusinessArea())
                )
                .orderBy(companyEntity.name.desc())
                .offset(filter.getOffset())
                .limit(11)
                .fetch().stream()
                .map(teacherQueryCompaniesVO -> (TeacherCompaniesVO) teacherQueryCompaniesVO)
                .toList();
    }

    @Override
    public Long getTotalCompanyCount(CompanyFilter filter) {
        return queryFactory
                .select(companyEntity.count())
                .from(companyEntity)
                .where(
                        eqCompanyType(filter.getType()),
                        containsName(filter.getName()),
                        eqRegion(filter.getRegion()),
                        eqBusinessArea(filter.getBusinessArea())
                ).fetchOne();
    }

    public JPQLQuery<Long> acceptancesCount() {
        return select(acceptanceEntity.count())
                .from(acceptanceEntity)
                .where(acceptanceEntity.company.eq(companyEntity));
    }

    @Override
    public QueryCompanyDetailsVO queryCompanyDetails(Long companyId) {
        return queryFactory
                .select(
                        new QQueryCompanyDetailsVO(
                                companyEntity.bizNo,
                                companyEntity.name,
                                companyEntity.companyLogoUrl,
                                companyEntity.companyIntroduce,
                                companyEntity.address.mainZipCode,
                                companyEntity.address.mainAddress,
                                companyEntity.address.subZipCode,
                                companyEntity.address.subAddress,
                                companyEntity.manager.managerName,
                                companyEntity.manager.managerPhoneNo,
                                companyEntity.manager.subManagerName,
                                companyEntity.manager.subManagerPhoneNo,
                                companyEntity.fax,
                                companyEntity.email,
                                companyEntity.representative,
                                companyEntity.foundedAt,
                                companyEntity.workersCount,
                                companyEntity.take,
                                recruitmentEntity.id,
                                companyEntity.serviceName,
                                companyEntity.businessArea
                        )
                )
                .from(companyEntity)
                .leftJoin(recruitmentEntity)
                .on(
                        recruitmentEntity.company.id.eq(companyEntity.id),
                        recentRecruitment(RecruitStatus.RECRUITING)
                )
                .where(companyEntity.id.eq(companyId))
                .fetchOne();
    }

    @Override
    public List<TeacherEmployCompaniesVO> queryEmployCompanies(String name, CompanyType type, Integer year) {
        return queryFactory
                .select(
                        new QQueryTeacherEmployCompaniesVO(
                                companyEntity.id,
                                companyEntity.name,
                                applicationEntity.countDistinct(),
                                acceptanceEntity.countDistinct()
                        )
                )
                .from(companyEntity)
                .leftJoin(companyEntity.acceptances, acceptanceEntity)
                .leftJoin(companyEntity.recruitments, recruitmentEntity)
                .on(
                        recruitmentEntity.company.id.eq(companyEntity.id),
                        recentRecruitment(null)
                )
                .leftJoin(recruitmentEntity.applications, applicationEntity)
                .on(applicationEntity.applicationStatus.eq(ApplicationStatus.FIELD_TRAIN))
                .where(
                        containsName(name),
                        eqCompanyType(type),
                        eqYear(year)
                )
                .orderBy(companyEntity.name.asc())
                .groupBy(companyEntity.id, companyEntity.name)
                .fetch().stream()
                .map(teacherEmployCompaniesVO -> (TeacherEmployCompaniesVO) teacherEmployCompaniesVO)
                .toList();
    }

    @Override
    public List<String> queryCompanyAttachmentUrls(Long companyId) {
        return queryFactory
                .select(companyAttachmentEntity.attachmentUrl)
                .from(companyAttachmentEntity)
                .where(companyAttachmentEntity.company.id.eq(companyId))
                .fetch();
    }

    @Override
    public Optional<Company> queryCompanyById(Long companyId) {
        return companyJpaRepository.findById(companyId)
                .map(companyMapper::toDomain);
    }

    @Override
    public List<Company> queryCompaniesByIdIn(List<Long> companyIds) {
        return companyJpaRepository.findAllByIdIn(companyIds).stream()
                .map(companyMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsCompanyByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    @Override
    public boolean existsCompanyById(Long companyId) {
        return companyJpaRepository.existsById(companyId);
    }

    //==conditions==//

    private BooleanExpression containsName(String name) {
        return name == null ? null : companyEntity.name.contains(name);
    }

    private BooleanExpression eqCompanyType(CompanyType type) {
        return type == null ? null : companyEntity.type.eq(type);
    }

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitmentEntity.recruitYear.eq(year);
    }

    private BooleanExpression recentRecruitment(RecruitStatus status) {
        return recruitmentEntity.createdAt.eq(
                select(recruitmentEntity.createdAt.max())
                        .from(recruitmentEntity)
                        .where(
                                recruitmentEntity.company.id.eq(companyEntity.id),
                                eqRecruitmentStatus(status)
                        )
        );
    }

    private BooleanExpression eqRecruitmentStatus(RecruitStatus status) {
        return status == null ? null : recruitmentEntity.status.eq(status);
    }


    private BooleanExpression eqRegion(String region) {
        return region == null ? null : companyEntity.address.mainAddress.startsWith(region);
    }

    private BooleanExpression eqBusinessArea(String businessArea) {
        return businessArea == null ? null : companyEntity.businessArea.eq(businessArea);
    }
}
