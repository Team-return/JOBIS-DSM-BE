package team.retum.jobis.domain.recruitment.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.QApplicationEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeId;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.persistence.repository.RecruitAreaCodeJpaRepository;
import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitAreaMapper;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitmentMapper;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryMyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitAreaVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryStudentRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryTeacherRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;
import team.retum.jobis.global.util.ExpressionUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final RecruitmentMapper recruitmentMapper;
    private final RecruitAreaMapper recruitAreaMapper;
    private final CodeJpaRepository codeJpaRepository;

    @Override
    public List<StudentRecruitmentVO> queryStudentRecruitmentsByFilter(RecruitmentFilter filter) {
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
                                bookmarkEntity.recruitment.id.isNotNull()
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
                        eqYear(filter.getYear()),
                        containsName(filter.getCompanyName()),
                        containsCodes(filter.getCodes()),
                        eqWinterIntern(filter.getWinterIntern()),
                        recruitmentEntity.status.eq(RecruitStatus.RECRUITING)
                )
                .offset(filter.getOffset())
                .limit(filter.getLimit())
                .orderBy(recruitmentEntity.createdAt.desc())
                .groupBy(recruitmentEntity.id)
                .fetch().stream()
                .map(StudentRecruitmentVO.class::cast)
                .toList();
    }

    @Override
    public List<TeacherRecruitmentVO> queryTeacherRecruitmentsByFilter(RecruitmentFilter filter) {
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
                                companyEntity.id
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
                        approvedApplication.applicationStatus.eq(ApplicationStatus.APPROVED)
                )
                .where(
                        eqYear(filter.getYear()),
                        betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                        eqRecruitStatus(filter.getStatus()),
                        containsName(filter.getCompanyName()),
                        eqWinterIntern(filter.getWinterIntern())
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
    public RecruitmentDetailVO queryRecruitmentDetailByIdAndStudentId(Long recruitmentId, Long userId) {
        return queryFactory
                .select(
                        new QQueryRecruitmentDetailVO(
                                recruitmentEntity.id,
                                companyEntity.id,
                                companyEntity.companyLogoUrl,
                                companyEntity.name,
                                recruitmentEntity.requiredGrade,
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
                                bookmarkEntity.isNotNull()
                        )
                )
                .from(recruitmentEntity)
                .join(recruitmentEntity.company, companyEntity)
                .leftJoin(bookmarkEntity)
                .on(
                        recruitmentEntity.id.eq(bookmarkEntity.recruitment.id),
                        bookmarkEntity.student.id.eq(userId)
                )
                .where(recruitmentEntity.id.eq(recruitmentId))
                .fetchOne();
    }

    @Override
    public Long getRecruitmentCountByFilter(RecruitmentFilter filter) {
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
    public List<RecruitAreaResponse> queryRecruitAreasByRecruitmentId(Long recruitmentId) {
        return queryFactory
                .selectFrom(recruitAreaEntity)
                .join(recruitAreaCodeEntity)
                .on(recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id))
                .join(recruitAreaCodeEntity.code, codeEntity)
                .where(recruitAreaEntity.recruitment.id.eq(recruitmentId))
                .transform(
                        groupBy(recruitAreaEntity.id)
                                .list(
                                        new QQueryRecruitAreaVO(
                                                recruitAreaEntity.id,
                                                recruitAreaEntity.hiredCount,
                                                recruitAreaEntity.majorTask,
                                                recruitAreaEntity.preferentialTreatment,
                                                list(codeEntity)
                                        )
                                )
                ).stream()
                .map(RecruitAreaResponse.class::cast)
                .toList();
    }

    @Override
    public Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId) {
        return Optional.ofNullable(
                        queryFactory
                                .selectFrom(recruitmentEntity)
                                .where(recruitmentEntity.company.id.eq(companyId))
                                .orderBy(recruitmentEntity.createdAt.desc())
                                .fetchFirst()
                )
                .map(recruitmentMapper::toDomain);
    }

    @Override
    public List<Recruitment> queryAllRecruitments() {
        return recruitmentJpaRepository.findAll().stream()
                .map(recruitmentMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<RecruitArea> queryRecruitmentAreaById(Long recruitAreaId) {
        return recruitAreaJpaRepository.findById(recruitAreaId).map(recruitAreaMapper::toDomain);
    }

    @Override
    public Long queryRecruitmentAreaCountByRecruitmentId(Long recruitmentId) {
        return queryFactory
                .select(recruitAreaEntity.count())
                .from(recruitAreaEntity)
                .where(recruitAreaEntity.recruitment.id.eq(recruitmentId))
                .fetchOne();
    }

    @Override
    public void deleteRecruitAreaById(Long recruitAreaId) {
        recruitAreaJpaRepository.deleteById(recruitAreaId);
    }

    @Override
    public void saveAllRecruitments(List<Recruitment> recruitments) {
        recruitmentJpaRepository.saveAll(
                recruitments.stream()
                        .map(recruitmentMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public Optional<Recruitment> queryRecruitmentById(Long recruitmentId) {
        return recruitmentJpaRepository.findById(recruitmentId).map(recruitmentMapper::toDomain);
    }

    @Override
    public void deleteRecruitment(Recruitment recruitment) {
        recruitAreaJpaRepository.deleteByRecruitmentId(recruitment.getId());
        recruitmentJpaRepository.delete(
                recruitmentMapper.toEntity(recruitment)
        );
    }

    @Override
    public Recruitment saveRecruitment(Recruitment recruitment) {
        return recruitmentMapper.toDomain(
                recruitmentJpaRepository.save(recruitmentMapper.toEntity(recruitment))
        );
    }

    @Override
    public RecruitArea saveRecruitmentArea(RecruitArea recruitArea) {
        RecruitAreaEntity recruitAreaEntity = recruitAreaJpaRepository.save(
                recruitAreaMapper.toEntity(recruitArea)
        );

        List<RecruitAreaCodeEntity> recruitAreaCodeEntities = codeJpaRepository.findCodesByCodeIn(
                        recruitArea.getCodes().values().stream()
                                .flatMap(Collection::stream)
                                .toList()
                ).stream()
                .map(code ->
                        new RecruitAreaCodeEntity(
                                new RecruitAreaCodeId(recruitAreaEntity.getId(), code.getCode()),
                                recruitAreaEntity,
                                code,
                                code.getType()
                        )
                )
                .toList();
        recruitAreaCodeJpaRepository.deleteAllByRecruitAreaId(recruitArea.getId());
        recruitAreaCodeJpaRepository.saveAll(recruitAreaCodeEntities);

        return recruitAreaMapper.toDomain(recruitAreaEntity);
    }

    @Override
    public void saveAllRecruitmentAreas(List<RecruitArea> recruitAreas) {
        recruitAreas.forEach(this::saveRecruitmentArea);
    }

    public List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds) {
        return recruitmentJpaRepository.findByIdIn(recruitmentIds).stream()
                .map(recruitmentMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsOnRecruitmentByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern) {
        return recruitmentJpaRepository.existsByCompanyIdAndStatusNotAndWinterIntern(companyId, RecruitStatus.DONE, winterIntern);
    }

    @Override
    public Map<Long, String> queryCompanyNameByRecruitmentIds(List<Long> recruitmentIds) {
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
    public List<MyAllRecruitmentsVO> queryMyAllRecruitmentsVOByCompanyId(Long companyId) {
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

    //===conditions===//

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitmentEntity.recruitYear.eq(year);
    }

    private BooleanExpression betweenRecruitDate(LocalDate start, LocalDate end) {
        if (start == null && end == null) return null;

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

    private BooleanExpression containsName(String name) {
        if (name == null) return null;

        return companyEntity.name.contains(name);
    }

    private BooleanExpression containsCodes(List<Long> codes) {
        return !codes.isEmpty() ?
                recruitAreaEntity.recruitAreaCodes.any().code.code.in(codes)
                : null;
    }

    private BooleanExpression eqWinterIntern(Boolean winterIntern) {
        return winterIntern == null ? null : recruitmentEntity.winterIntern.eq(winterIntern);
    }
}