package team.retum.jobis.domain.company.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.QApplicationEntity;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse.CompanyResponse;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.persistence.entity.QCompanyEntity;
import team.retum.jobis.domain.company.persistence.mapper.CompanyMapper;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.company.persistence.repository.vo.QQueryCompanyDetailsVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QQueryReviewAvailableCompanyVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QQueryTeacherEmployCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QStudentQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.QTeacherQueryCompaniesVO;
import team.retum.jobis.domain.company.persistence.repository.vo.TeacherQueryCompaniesVO;
import team.retum.jobis.domain.company.spi.CompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity;
import team.retum.jobis.domain.student.persistence.entity.QStudentEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.acceptance.persistence.entity.QAcceptanceEntity.acceptanceEntity;
import static team.retum.jobis.domain.application.model.ApplicationStatus.APPROVED;
import static team.retum.jobis.domain.application.model.ApplicationStatus.FAILED;
import static team.retum.jobis.domain.application.model.ApplicationStatus.FIELD_TRAIN;
import static team.retum.jobis.domain.application.model.ApplicationStatus.PASS;
import static team.retum.jobis.domain.application.persistence.entity.QApplicationEntity.applicationEntity;
import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;
import static team.retum.jobis.domain.review.persistence.entity.QReviewEntity.reviewEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@Repository
@RequiredArgsConstructor
public class CompanyPersistenceAdapter implements CompanyPort {

    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyMapper companyMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Company save(Company company) {
        return companyMapper.toDomain(
            companyJpaRepository.save(
                companyMapper.toEntity(company)
            )
        );
    }

    @Override
    public void saveAll(List<Company> companies) {
        companyJpaRepository.saveAll(
            companies.stream()
                .map(companyMapper::toEntity)
                .toList()
        );
    }

    @Override
    public List<StudentCompaniesVO> getStudentCompanies(CompanyFilter filter) {
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
            .limit(filter.getLimit())
            .fetch().stream()
            .map(StudentCompaniesVO.class::cast)
            .toList();
    }

    @Override
    public List<TeacherCompaniesVO> getByConditions(CompanyFilter filter) {
        JPAQuery<TeacherQueryCompaniesVO> query = queryFactory
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
                    acceptancesCount(),
                    reviewCount()
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
            .orderBy(recruitmentEntity.recruitYear.desc());

        if (filter.getPage() != null) {
            query
                .offset(filter.getOffset())
                .limit(filter.getLimit());
        }


        return query
            .fetch().stream()
            .map(TeacherCompaniesVO.class::cast)
            .toList();
    }

    private JPQLQuery<Long> acceptancesCount() {
        return select(acceptanceEntity.count())
            .from(acceptanceEntity)
            .where(acceptanceEntity.company.eq(companyEntity));
    }

    private JPQLQuery<Long> reviewCount() {
        return select(reviewEntity.count())
            .from(reviewEntity)
            .where(reviewEntity.company.eq(companyEntity));
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

    @Override
    public Optional<CompanyDetailsVO> getCompanyDetails(Long companyId) {
        return Optional.ofNullable(
            queryFactory
                .select(
                    new QQueryCompanyDetailsVO(
                        companyEntity.bizNo,
                        companyEntity.name,
                        companyEntity.companyLogoUrl,
                        companyEntity.companyIntroduce,
                        companyEntity.address.mainZipCode,
                        companyEntity.address.mainAddress,
                        companyEntity.address.mainAddressDetail,
                        companyEntity.manager.managerName,
                        companyEntity.manager.managerPhoneNo,
                        companyEntity.email,
                        companyEntity.representative,
                        companyEntity.representativePhoneNo,
                        companyEntity.foundedAt,
                        companyEntity.workersCount,
                        companyEntity.take,
                        recruitmentEntity.id,
                        companyEntity.serviceName,
                        codeEntity.code,
                        companyEntity.businessArea,
                        companyEntity.attachmentUrls,
                        companyEntity.bizRegistrationUrl,
                        companyEntity.headquarter
                    )
                )
                .from(companyEntity)
                .leftJoin(recruitmentEntity)
                .on(
                    recruitmentEntity.company.id.eq(companyEntity.id),
                    recentRecruitment(RecruitStatus.RECRUITING)
                )
                .join(codeEntity)
                .on(
                    codeEntity.keyword.eq(companyEntity.businessArea),
                    codeEntity.type.eq(CodeType.BUSINESS_AREA)
                )
                .where(companyEntity.id.eq(companyId))
                .fetchOne()
        );
    }

    @Override
    public List<TeacherEmployCompaniesVO> getEmployCompanies(CompanyFilter filter) {
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
            .leftJoin(acceptanceEntity)
            .on(acceptanceEntity.company.id.eq(companyEntity.id))
            .leftJoin(recruitmentEntity)
            .on(recruitmentEntity.company.id.eq(companyEntity.id))
            .on(
                recruitmentEntity.company.id.eq(companyEntity.id),
                recentRecruitment(null)
            )
            .leftJoin(applicationEntity)
            .on(
                applicationEntity.applicationStatus.eq(FIELD_TRAIN),
                applicationEntity.recruitment.id.eq(recruitmentEntity.id)
            )
            .where(
                containsName(filter.getName()),
                eqCompanyType(filter.getType()),
                eqYear(filter.getYear())
            )
            .orderBy(companyEntity.name.asc())
            .offset(filter.getOffset())
            .limit(filter.getLimit())
            .groupBy(companyEntity.id, companyEntity.name)
            .fetch().stream()
            .map(TeacherEmployCompaniesVO.class::cast)
            .toList();
    }

    @Override
    public Optional<Company> getById(Long companyId) {
        return companyJpaRepository.findById(companyId)
            .map(companyMapper::toDomain);
    }

    @Override
    public Optional<Company> getByBusinessNumber(String businessNumber) {
        return companyJpaRepository.findByBizNo(businessNumber)
            .map(companyMapper::toDomain);
    }

    @Override
    public List<Company> getByIdIn(List<Long> companyIds) {
        return companyJpaRepository.findAllByIdIn(companyIds).stream()
            .map(companyMapper::toDomain)
            .toList();
    }

    @Override
    public boolean existsByBizNo(String bizNo) {
        return companyJpaRepository.existsByBizNo(bizNo);
    }

    @Override
    public boolean existsById(Long companyId) {
        return companyJpaRepository.existsById(companyId);
    }

    @Override
    public List<CompanyResponse> getReviewAvailableCompaniesByStudentId(Long studentId) {
        return queryFactory
            .select(
                new QQueryReviewAvailableCompanyVO(
                    companyEntity.id,
                    companyEntity.name
                )
            )
            .from(companyEntity)
            .join(applicationEntity)
            .on(
                applicationEntity.student.id.eq(studentId),
                applicationEntity.applicationStatus.in(
                    List.of(PASS, FAILED, FIELD_TRAIN)
                )
            )
            .join(applicationEntity.recruitment, recruitmentEntity)
            .leftJoin(reviewEntity)
            .on(
                reviewEntity.student.id.eq(studentId),
                reviewEntity.company.id.eq(companyEntity.id)
            )
            .where(
                recruitmentEntity.company.id.eq(companyEntity.id),
                reviewEntity.isNull()
            )
            .groupBy(companyEntity.id)
            .fetch().stream()
            .map(CompanyResponse.class::cast)
            .toList();
    }

    @Override
    public Map<Long, String> getCompanyNameByRecruitmentIds(List<Long> recruitmentIds) {
        return queryFactory
            .selectFrom(recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .where(recruitmentEntity.id.in(recruitmentIds))
            .transform(
                groupBy(recruitmentEntity.id)
                    .as(companyEntity.name)
            );
    }

    @Override
    public Long countCompanies() {
        return queryFactory
            .select(companyEntity.count())
            .from(companyEntity)
            .fetchOne();
    }

    @Override
    public List<CompanyVO> getEmploymentRateByClassNumber(Integer classNum) {
        return queryFactory
            .select(
                Projections.constructor(CompanyVO.class,
                    companyEntity.id,
                    companyEntity.name,
                    companyEntity.companyLogoUrl
                )
            )
            .from(studentEntity)
            .innerJoin(applicationEntity).on(applicationEntity.student.id.eq(studentEntity.id))
            .innerJoin(recruitmentEntity).on(applicationEntity.recruitment.id.eq(recruitmentEntity.id)
                .and(applicationEntity.applicationStatus.eq(ApplicationStatus.PASS)))
            .innerJoin(companyEntity).on(recruitmentEntity.company.id.eq(companyEntity.id))
            .where(studentEntity.classRoom.eq(classNum))
            .groupBy(companyEntity.id)
            .fetch();
    }

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
