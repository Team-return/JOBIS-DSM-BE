package team.retum.jobis.domain.recruitment.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.QApplicationEntity;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.entity.QRecruitAreaCodeEntity;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.StudentRecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitmentExistsResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.entity.QRecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitmentMapper;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryMyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryStudentRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryTeacherRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryManualRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.ManualRecruitmentVO;
import team.retum.jobis.global.util.ExpressionUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.retum.jobis.domain.bookmark.persistence.entity.QBookmarkEntity.bookmarkEntity;
import static team.retum.jobis.domain.code.model.CodeType.JOB;
import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;
import static team.retum.jobis.domain.code.persistence.entity.QRecruitAreaCodeEntity.recruitAreaCodeEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitAreaEntity.recruitAreaEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;

@Repository
@RequiredArgsConstructor
public class RecruitmentPersistenceAdapter implements RecruitmentPort {

    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final RecruitmentMapper recruitmentMapper;

    @Override
    public List<StudentRecruitmentVO> getStudentRecruitmentsBy(StudentRecruitmentFilter filter) {
        StringExpression recruitJobsPath = ExpressionUtil.groupConcat(codeEntity.keyword);
        BooleanExpression codeFilter = matchesCodeFilter(filter.getJobCode(), filter.getTechCodes());

        return queryFactory
            .select(
                new QQueryStudentRecruitmentsVO(
                    recruitmentEntity.id,
                    companyEntity.name,
                    recruitmentEntity.payInfo.trainPay,
                    recruitmentEntity.militarySupport,
                    companyEntity.companyLogoUrl,
                    recruitJobsPath,
                    bookmarkEntity.recruitment.id.isNotNull(),
                    recruitmentEntity.status,
                    recruitmentEntity.recruitYear
                )
            )
            .from(recruitmentEntity)
            .leftJoin(bookmarkEntity)
            .on(
                recruitmentEntity.id.eq(bookmarkEntity.recruitment.id),
                bookmarkEntity.student.id.eq(filter.getStudentId())
            )
            .join(recruitmentEntity.company, companyEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
            .join(recruitAreaCodeEntity)
            .on(
                recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                recruitAreaCodeEntity.type.eq(JOB)
            )
            .join(recruitAreaCodeEntity.code, codeEntity)
            .where(
                eqYearsAndRecruitStatus(filter.getYears(), filter.getStatus(), filter.getCompanyName()),
                containsName(filter.getCompanyName()),
                eqWinterIntern(filter.getWinterIntern()),
                eqMilitarySupport(filter.getMilitarySupport()),
                codeFilter
            )
            .offset(filter.getOffset())
            .limit(filter.getLimit())
            .orderBy(recruitmentEntity.createdAt.desc())
            .groupBy(
                   recruitmentEntity.id,
                   companyEntity.name,
                   recruitmentEntity.payInfo.trainPay,
                   recruitmentEntity.militarySupport,
                   companyEntity.companyLogoUrl
            )
                .fetch().stream()
            .map(StudentRecruitmentVO.class::cast)
            .toList();
    }

    @Override
    public List<TeacherRecruitmentVO> getTeacherRecruitmentsBy(RecruitmentFilter filter) {
        QApplicationEntity requestedApplication = new QApplicationEntity("requestedApplication");
        QApplicationEntity approvedApplication = new QApplicationEntity("approvedApplication");

        StringExpression recruitJobsPath = ExpressionUtil.groupConcat(codeEntity.keyword);
        QRecruitAreaEntity subRecruitArea = new QRecruitAreaEntity("subRecruitArea");

        JPQLQuery<Long> totalHiredCountSubQuery = JPAExpressions
            .select(subRecruitArea.hiredCount.sum().longValue())
            .from(subRecruitArea)
            .where(subRecruitArea.recruitment.id.eq(recruitmentEntity.id));

        return queryFactory
            .select(
                new QQueryTeacherRecruitmentsVO(
                    recruitmentEntity.id,
                    recruitmentEntity.status,
                    recruitmentEntity.recruitDate.startDate,
                    recruitmentEntity.recruitDate.finishDate,
                    companyEntity.name,
                    companyEntity.type,
                    recruitJobsPath,
                    totalHiredCountSubQuery,
                    requestedApplication.countDistinct(),
                    approvedApplication.countDistinct(),
                    companyEntity.id,
                    recruitmentEntity.hireConvertible
                )
            )
            .from(recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
            .join(recruitAreaCodeEntity)
            .on(
                recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                recruitAreaCodeEntity.type.eq(JOB)
            )
            .join(recruitAreaCodeEntity.code, codeEntity)
            .leftJoin(requestedApplication)
            .on(
                requestedApplication.recruitment.id.eq(recruitmentEntity.id),
                requestedApplication.applicationStatus.eq(ApplicationStatus.REQUESTED)
            )
            .leftJoin(approvedApplication)
            .on(
                approvedApplication.recruitment.id.eq(recruitmentEntity.id),
                approvedApplication.applicationStatus.in(
                    ApplicationStatus.APPROVED,
                    ApplicationStatus.SEND,
                    ApplicationStatus.PROCESSING
                )
            )
            .where(
                eqYear(filter.getYear()),
                betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                eqRecruitStatus(filter.getStatus()),
                containsName(filter.getCompanyName()),
                eqWinterIntern(filter.getWinterIntern()),
                containsCodes(filter.getCodes())
            )
            .offset(filter.getOffset())
            .limit(filter.getLimit())
            .orderBy(recruitmentEntity.createdAt.desc())
            .groupBy(recruitmentEntity.id)
            .fetch().stream()
            .map(TeacherRecruitmentVO.class::cast)
            .toList();
    }

    @Override
    public List<TeacherRecruitmentVO> getTeacherRecruitmentsWithoutPageBy(RecruitmentFilter filter) {
        QApplicationEntity requestedApplication = new QApplicationEntity("requestedApplication");
        QApplicationEntity approvedApplication = new QApplicationEntity("approvedApplication");

        StringExpression recruitJobsPath = ExpressionUtil.groupConcat(codeEntity.keyword);
        return queryFactory
            .select(
                new QQueryTeacherRecruitmentsVO(
                    recruitmentEntity.id,
                    recruitmentEntity.status,
                    recruitmentEntity.recruitDate.startDate,
                    recruitmentEntity.recruitDate.finishDate,
                    companyEntity.name,
                    companyEntity.type,
                    recruitJobsPath,
                    recruitAreaEntity.hiredCount.sum().divide(recruitAreaEntity.hiredCount.count()).longValue(),
                    requestedApplication.countDistinct(),
                    approvedApplication.countDistinct(),
                    companyEntity.id,
                    recruitmentEntity.hireConvertible
                )
            )
            .from(recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
            .join(recruitAreaCodeEntity)
            .on(
                recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                recruitAreaCodeEntity.type.eq(JOB)
            )
            .join(recruitAreaCodeEntity.code, codeEntity)
            .leftJoin(requestedApplication)
            .on(
                requestedApplication.recruitment.id.eq(recruitmentEntity.id),
                requestedApplication.applicationStatus.eq(ApplicationStatus.REQUESTED)
            )
            .leftJoin(approvedApplication)
            .on(
                approvedApplication.applicationStatus.in(
                    ApplicationStatus.APPROVED,
                    ApplicationStatus.SEND,
                    ApplicationStatus.PROCESSING
                )
            )
            .where(
                eqYear(filter.getYear()),
                containsCodes(filter.getCodes()),
                eqWinterIntern(filter.getWinterIntern()),
                eqMilitarySupport(filter.getMilitarySupport())
            )
            .orderBy(recruitmentEntity.createdAt.desc())
            .groupBy(recruitmentEntity.id)
            .fetch().stream()
            .map(TeacherRecruitmentVO.class::cast)
            .toList();
    }

    @Override
    public RecruitmentDetailVO getByIdAndStudentIdOrThrow(Long recruitmentId, Long studentId) {
        return Optional.ofNullable(
                queryFactory
                    .select(
                        new QQueryRecruitmentDetailVO(
                            recruitmentEntity.id,
                            companyEntity.id,
                            companyEntity.companyLogoUrl,
                            companyEntity.name,
                            recruitmentEntity.additionalQualifications,
                            recruitmentEntity.workingHours,
                            recruitmentEntity.flexibleWorking,
                            recruitmentEntity.requiredLicenses,
                            recruitmentEntity.hiringProgress,
                            recruitmentEntity.payInfo.trainPay,
                            recruitmentEntity.payInfo.pay,
                            recruitmentEntity.benefits,
                            recruitmentEntity.militarySupport,
                            recruitmentEntity.submitDocument,
                            recruitmentEntity.recruitDate.startDate,
                            recruitmentEntity.recruitDate.finishDate,
                            recruitmentEntity.etc,
                            companyEntity.bizNo,
                            recruitmentEntity.winterIntern,
                            bookmarkEntity.isNotNull(),
                            recruitmentEntity.hireConvertible,
                            recruitmentEntity.integrationPlan
                        )
                    )
                    .from(recruitmentEntity)
                    .join(recruitmentEntity.company, companyEntity)
                    .leftJoin(bookmarkEntity)
                    .on(
                        recruitmentEntity.id.eq(bookmarkEntity.recruitment.id),
                        bookmarkEntity.student.id.eq(studentId)
                    )
                    .where(recruitmentEntity.id.eq(recruitmentId))
                    .fetchOne()
            )
            .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    @Override
    public Long getCountBy(RecruitmentFilter filter) {
        if (filter.getCodes().isEmpty()) {
            return queryFactory
                .select(recruitmentEntity.count())
                .from(recruitmentEntity)
                .join(recruitmentEntity.company, companyEntity)
                .where(
                    eqYear(filter.getYear()),
                    betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                    eqRecruitStatus(filter.getStatus()),
                    containsName(filter.getCompanyName()),
                    eqWinterIntern(filter.getWinterIntern())
                )
                .fetchOne();
        } else {
            return queryFactory
                .select(recruitmentEntity.count())
                .from(recruitmentEntity)
                .join(recruitmentEntity.company, companyEntity)
                .join(recruitAreaEntity)
                .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
                .leftJoin(recruitAreaCodeEntity)
                .on(
                    recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                    recruitAreaCodeEntity.code.code.in(filter.getCodes())
                )
                .where(
                    eqYear(filter.getYear()),
                    betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                    eqRecruitStatus(filter.getStatus()),
                    containsName(filter.getCompanyName()),
                    recruitAreaCodeEntity.recruitAreaCodeId.recruitAreaId.isNotNull(),
                    eqWinterIntern(filter.getWinterIntern())
                )
                .fetchOne();
        }
    }

    @Override
    public Long getCountByStudentFilter(StudentRecruitmentFilter filter) {
        BooleanExpression codeFilter = matchesCodeFilter(filter.getJobCode(), filter.getTechCodes());

        return queryFactory
            .select(recruitmentEntity.countDistinct())
            .from(recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
            .join(recruitAreaCodeEntity)
            .on(
                recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                recruitAreaCodeEntity.type.eq(JOB)
            )
            .join(recruitAreaCodeEntity.code, codeEntity)
            .where(
                eqYearsAndRecruitStatus(filter.getYears(), filter.getStatus(), filter.getCompanyName()),
                containsName(filter.getCompanyName()),
                eqWinterIntern(filter.getWinterIntern()),
                eqMilitarySupport(filter.getMilitarySupport()),
                codeFilter
            )
            .fetchOne();
    }

    @Override
    public Recruitment getRecentByCompanyIdOrThrow(Long companyId) {
        return recruitmentMapper.toDomain(
            Optional.ofNullable(
                    queryFactory
                        .selectFrom(recruitmentEntity)
                        .where(eqCompanyId(companyId))
                        .orderBy(recruitmentEntity.createdAt.desc())
                        .fetchFirst()
                )
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION)
        );
    }

    @Override
    public List<Recruitment> getAll() {
        return recruitmentJpaRepository.findAll().stream()
            .map(recruitmentMapper::toDomain)
            .toList();
    }

    @Override
    public void saveAll(List<Recruitment> recruitments) {
        recruitmentJpaRepository.saveAll(
            recruitments.stream()
                .map(recruitmentMapper::toEntity)
                .toList()
        );
    }

    @Override
    public Recruitment getByIdOrThrow(Long recruitmentId) {
        return this.getById(recruitmentId)
            .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    @Override
    public Optional<Recruitment> getById(Long recruitmentId) {
        return recruitmentJpaRepository.findById(recruitmentId)
            .map(recruitmentMapper::toDomain);
    }

    @Override
    public void delete(Recruitment recruitment) {
        recruitAreaJpaRepository.deleteByRecruitmentId(recruitment.getId());
        recruitmentJpaRepository.delete(
            recruitmentMapper.toEntity(recruitment)
        );
    }

    @Override
    public Recruitment save(Recruitment recruitment) {
        return recruitmentMapper.toDomain(
            recruitmentJpaRepository.save(recruitmentMapper.toEntity(recruitment))
        );
    }

    public List<Recruitment> getAllByIdInOrThrow(List<Long> recruitmentIds) {
        List<Recruitment> result = recruitmentJpaRepository.findByIdIn(recruitmentIds).stream()
            .map(recruitmentMapper::toDomain)
            .toList();
        if (result.size() != recruitmentIds.size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        return result;
    }

    @Override
    public boolean existsByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern) {
        return recruitmentJpaRepository.existsByCompanyIdAndWinterIntern(companyId, winterIntern);
    }

    @Override
    public List<MyAllRecruitmentsVO> getAllByCompanyId(Long companyId) {
        return queryFactory
            .selectFrom(recruitmentEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.eq(recruitmentEntity))
            .join(recruitAreaEntity.recruitAreaCodes, recruitAreaCodeEntity)
            .on(recruitAreaCodeEntity.type.eq(JOB))
            .join(recruitAreaCodeEntity.code, codeEntity)
            .join(recruitmentEntity.company, companyEntity)
            .where(companyEntity.id.eq(companyId))
            .orderBy(recruitmentEntity.createdAt.desc())
            .transform(
                groupBy(recruitmentEntity.id)
                    .list(
                        new QQueryMyAllRecruitmentsVO(
                            recruitmentEntity.id,
                            list(codeEntity),
                            recruitAreaEntity.hiredCount,
                            recruitmentEntity.createdAt
                        )
                    )
            ).stream().map(MyAllRecruitmentsVO.class::cast)
            .toList();
    }

    @Override
    public List<Recruitment> getRecent() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayAgo = now.minusDays(1);

        return recruitmentJpaRepository.findByCreationDateBetween(oneDayAgo, now);
    }

    @Override
    public RecruitmentExistsResponse existsByCompanyId(Long companyId) {
        boolean winterInternExists = queryFactory
            .selectOne()
            .from(recruitmentEntity)
            .where(
                eqCompanyId(companyId),
                eqWinterIntern(true),
                eqRecruitStatus(RecruitStatus.RECRUITING)
            ).fetchFirst() != null;

        boolean experientialExists = queryFactory
            .selectOne()
            .from(recruitmentEntity)
            .where(
                eqCompanyId(companyId),
                eqWinterIntern(false),
                eqRecruitStatus(RecruitStatus.RECRUITING)
            ).fetchFirst() != null;

        return new RecruitmentExistsResponse(winterInternExists, experientialExists);
    }

    @Override
    public List<StudentRecruitmentVO> getStudentRecruitmentByCompanyNames(List<String> companyNames, Long studentId) {
        StringExpression recruitJobsPath = ExpressionUtil.groupConcat(codeEntity.keyword);
        return queryFactory
            .select(
                new QQueryStudentRecruitmentsVO(
                    recruitmentEntity.id,
                    companyEntity.name,
                    recruitmentEntity.payInfo.trainPay,
                    recruitmentEntity.militarySupport,
                    companyEntity.companyLogoUrl,
                    recruitJobsPath,
                    bookmarkEntity.recruitment.id.isNotNull(),
                    recruitmentEntity.status,
                    recruitmentEntity.recruitYear
                )
            )
            .from(recruitmentEntity)
            .leftJoin(bookmarkEntity)
            .on(
                recruitmentEntity.id.eq(bookmarkEntity.recruitment.id),
                bookmarkEntity.student.id.eq(studentId)
            )
            .join(recruitmentEntity.company, companyEntity)
            .join(recruitAreaEntity)
            .on(recruitAreaEntity.recruitment.id.eq(recruitmentEntity.id))
            .join(recruitAreaCodeEntity)
            .on(
                recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id),
                recruitAreaCodeEntity.type.eq(JOB)
            )
            .join(recruitAreaCodeEntity.code, codeEntity)
            .where(companyEntity.name.in(companyNames))
            .orderBy(recruitmentEntity.createdAt.desc())
            .groupBy(recruitmentEntity.id)
            .fetch()
            .stream()
            .map(StudentRecruitmentVO.class::cast)
            .toList();
    }

    @Override
    public List<ManualRecruitmentVO> getTeacherManualRecruitments() {
        return queryFactory
            .select(
                new QQueryManualRecruitmentVO(
                    recruitmentEntity.id,
                    companyEntity.name,
                    companyEntity.companyLogoUrl
                )
            )
            .from(recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .where(
                eqRecruitStatus(RecruitStatus.MANUAL_ADD),
                eqYear(Year.now().getValue())
            )
            .orderBy(recruitmentEntity.createdAt.desc())
            .fetch()
            .stream()
            .map(ManualRecruitmentVO.class::cast)
            .toList();
    }

    @Override
    public List<Recruitment> getByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern) {
        return queryFactory
            .selectFrom(recruitmentEntity)
            .where(
                eqCompanyId(companyId),
                eqWinterIntern(winterIntern)
            )
            .limit(1)
            .fetch()
            .stream()
            .map(recruitmentMapper::toDomain)
            .toList();
    }

    //===conditions===//

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitmentEntity.recruitYear.eq(year);
    }

    private BooleanExpression eqYears(List<Integer> years) {
        return (years == null || years.isEmpty()) ? null : recruitmentEntity.recruitYear.in(years);
    }

    private BooleanExpression betweenRecruitDate(LocalDate start, LocalDate end) {
        if (start == null && end == null) {
            return null;
        }

        if (start == null) {
            return recruitmentEntity.recruitDate.finishDate.before(end.plusDays(1));
        }

        if (end == null) {
            return recruitmentEntity.recruitDate.startDate.after(start.minusDays(1));
        }

        return recruitmentEntity.recruitDate.startDate.after(start.minusDays(1))
            .and(recruitmentEntity.recruitDate.finishDate.before(end.plusDays(1)));
    }

    private BooleanExpression eqRecruitStatus(RecruitStatus status) {
        return status == null ? null : recruitmentEntity.status.eq(status);
    }

    private BooleanExpression eqRecruitStatuses(List<RecruitStatus> statuses) {
        return (statuses == null || statuses.isEmpty()) ? null : recruitmentEntity.status.in(statuses);
    }

    private BooleanExpression eqYearsAndRecruitStatus(List<Integer> years, RecruitStatus status, String companyName) {
        boolean noYears = years == null || years.isEmpty();
        boolean noStatus = status == null;
        boolean hasCompanyName = companyName != null && !companyName.isBlank();

        if (noYears && noStatus) {
            if (hasCompanyName) {
                return eqRecruitStatuses(List.of(
                    RecruitStatus.RECRUITING,
                    RecruitStatus.DONE
                ));
            }

            return eqYear(Year.now().getValue())
                .and(eqRecruitStatus(RecruitStatus.RECRUITING));
        }

        if (noYears) {
            return eqRecruitStatus(status);
        }

        if (noStatus) {
            return eqYears(years)
                .and(eqRecruitStatuses(List.of(
                    RecruitStatus.RECRUITING,
                    RecruitStatus.DONE
                )));
        }

        return eqYears(years)
            .and(eqRecruitStatus(status));
    }

    private BooleanExpression containsName(String name) {
        if (name == null) {
            return null;
        }

        return companyEntity.name.contains(name);
    }

    private BooleanExpression containsCodes(List<Long> codes) {
        return !codes.isEmpty()
            ? recruitAreaEntity.recruitAreaCodes.any().code.code.in(codes)
            : null;
    }

    private BooleanExpression eqWinterIntern(Boolean winterIntern) {
        return winterIntern == null ? null : recruitmentEntity.winterIntern.eq(winterIntern);
    }

    private BooleanExpression eqMilitarySupport(Boolean militarySupport) {
        return militarySupport == null ? null : recruitmentEntity.militarySupport.eq(militarySupport);
    }

    private BooleanExpression eqCompanyId(Long companyId) {
        return companyId == null ? null : recruitmentEntity.company.id.eq(companyId);
    }

    private BooleanExpression matchesCodeFilter(Long jobCode, List<Long> techCodes) {
        BooleanExpression hasJobCode = hasJobCode(jobCode);
        BooleanExpression hasTechCode = hasTechCode(techCodes);

        if (hasJobCode != null && hasTechCode != null) {
            return hasJobCode.and(hasTechCode);
        } else if (hasJobCode != null) {
            return hasJobCode;
        } else if (hasTechCode != null) {
            return hasTechCode;
        }
        return null;
    }

    private BooleanExpression hasJobCode(Long jobCode) {
        if (jobCode == null) {
            return null;
        }

        QRecruitAreaEntity recruitAreaSub = new QRecruitAreaEntity("recruitAreaSub");
        QRecruitAreaCodeEntity recruitAreaCodeSub = new QRecruitAreaCodeEntity("recruitAreaCodeSub");

        return JPAExpressions
            .selectOne()
            .from(recruitAreaSub)
            .join(recruitAreaCodeSub)
            .on(
                recruitAreaCodeSub.recruitArea.id.eq(recruitAreaSub.id),
                recruitAreaCodeSub.type.eq(CodeType.JOB),
                recruitAreaCodeSub.code.code.in(jobCode)
            )
            .where(recruitAreaSub.recruitment.id.eq(recruitmentEntity.id))
            .exists();
    }

    private BooleanExpression hasTechCode(List<Long> techCodes) {
        if (techCodes == null || techCodes.isEmpty()) {
            return null;
        }

        QRecruitAreaEntity recruitAreaSub = new QRecruitAreaEntity("recruitAreaSub");
        QRecruitAreaCodeEntity recruitAreaCodeSub = new QRecruitAreaCodeEntity("recruitAreaCodeSub");

        return JPAExpressions
            .selectOne()
            .from(recruitAreaSub)
            .join(recruitAreaCodeSub)
            .on(
                recruitAreaCodeSub.recruitArea.id.eq(recruitAreaSub.id),
                recruitAreaCodeSub.type.eq(CodeType.TECH),
                recruitAreaCodeSub.code.code.in(techCodes)
            )
            .where(recruitAreaSub.recruitment.id.eq(recruitmentEntity.id))
            .exists();
    }
}
