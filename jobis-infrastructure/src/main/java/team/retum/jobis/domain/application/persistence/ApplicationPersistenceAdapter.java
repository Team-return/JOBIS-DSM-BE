package team.retum.jobis.domain.application.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.mapper.ApplicationMapper;
import team.retum.jobis.domain.application.persistence.repository.ApplicationJpaRepository;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryApplicationDetailVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryApplicationVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryFieldTraineesVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.application.spi.ApplicationPort;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.application.model.ApplicationStatus.FIELD_TRAIN;
import static team.retum.jobis.domain.application.model.ApplicationStatus.PASS;
import static team.retum.jobis.domain.application.persistence.entity.QApplicationAttachmentEntity.applicationAttachmentEntity;
import static team.retum.jobis.domain.application.persistence.entity.QApplicationEntity.applicationEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitmentEntity.recruitmentEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class ApplicationPersistenceAdapter implements ApplicationPort {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationMapper applicationMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationVO> getAllByConditions(ApplicationFilter filter) {
        JPAQuery<ApplicationEntity> query = queryFactory
            .selectFrom(applicationEntity)
            .join(applicationEntity.student, studentEntity)
            .join(applicationEntity.recruitment, recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .leftJoin(applicationEntity.applicationAttachments, applicationAttachmentEntity)
            .where(
                eqRecruitmentId(filter.getRecruitmentId()),
                eqStudentId(filter.getStudentId()),
                eqApplicationStatus(filter.getApplicationStatus()),
                containStudentName(filter.getStudentName()),
                eqWinterIntern(filter.getWinterIntern()),
                eqYear(filter.getYear())
            )
            .orderBy(
                applicationEntity.updatedAt.desc(),
                applicationEntity.createdAt.desc()
            );

        return query
            .transform(
                groupBy(applicationEntity.id)
                    .list(
                        new QQueryApplicationVO(
                            applicationEntity.id,
                            recruitmentEntity.id,
                            studentEntity.name,
                            studentEntity.grade,
                            studentEntity.number,
                            studentEntity.classRoom,
                            studentEntity.profileImageUrl,
                            companyEntity.name,
                            companyEntity.companyLogoUrl,
                            list(applicationAttachmentEntity),
                            applicationEntity.createdAt,
                            applicationEntity.applicationStatus
                        )
                    )
            )
            .stream()
            .map(application -> ApplicationVO.builder()
                .id(application.getId())
                .recruitmentId(application.getRecruitmentId())
                .name(application.getName())
                .grade(application.getGrade())
                .number(application.getNumber())
                .classNumber(application.getClassNumber())
                .companyLogoUrl(application.getCompanyLogoUrl())
                .profileImageUrl(application.getProfileImageUrl())
                .companyName(application.getCompanyName())
                .createdAt(application.getCreatedAt())
                .applicationStatus(application.getApplicationStatus())
                .applicationAttachmentEntities(
                    application.getApplicationAttachmentEntities().stream()
                        .map(attachment -> new ApplicationAttachment(attachment.getAttachmentUrl(), attachment.getType()))
                        .toList()
                )
                .build()
            )
            .toList();
    }

    @Override
    public Long getCountByCondition(ApplicationStatus applicationStatus, String studentName, Boolean winterIntern) {
        return queryFactory
            .select(applicationEntity.count())
            .from(applicationEntity)
            .join(applicationEntity.student, studentEntity)
            .where(
                eqApplicationStatus(applicationStatus),
                containStudentName(studentName),
                eqWinterIntern(winterIntern)
            ).fetchOne();
    }

    @Override
    public List<FieldTraineesVO> getFieldTraineesByCompanyId(Long companyId) {
        return queryFactory
            .select(
                new QQueryFieldTraineesVO(
                    applicationEntity.id,
                    studentEntity.grade,
                    studentEntity.classRoom,
                    studentEntity.number,
                    studentEntity.name,
                    applicationEntity.startDate,
                    applicationEntity.endDate
                )
            )
            .from(applicationEntity)
            .join(applicationEntity.student, studentEntity)
            .on(applicationEntity.student.id.eq(studentEntity.id))
            .join(applicationEntity.recruitment, recruitmentEntity)
            .on(recentRecruitment(companyId))
            .where(applicationEntity.applicationStatus.eq(FIELD_TRAIN))
            .fetch().stream()
            .map(FieldTraineesVO.class::cast)
            .toList();
    }

    @Override
    public List<PassedApplicationStudentsVO> getPassedStudentsByCompanyId(Long companyId) {
        return queryFactory
            .select(
                new QQueryPassedApplicationStudentsVO(
                    applicationEntity.id,
                    studentEntity.name,
                    studentEntity.grade,
                    studentEntity.classRoom,
                    studentEntity.number
                )
            )
            .from(applicationEntity)
            .join(applicationEntity.student, studentEntity)
            .join(applicationEntity.recruitment, recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .where(
                companyEntity.id.eq(companyId),
                applicationEntity.applicationStatus.eq(PASS)
            )
            .fetch().stream()
            .map(PassedApplicationStudentsVO.class::cast)
            .toList();
    }

    @Override
    public Application save(Application application) {
        return applicationMapper.toDomain(
            applicationJpaRepository.save(applicationMapper.toEntity(application))
        );
    }

    @Override
    public void deleteByIds(List<Long> applicationIds) {
        applicationJpaRepository.deleteByIdIn(applicationIds);
    }

    @Override
    public List<Application> getAllByIdInOrThrow(List<Long> applicationIds) {
        List<ApplicationEntity> applications = applicationJpaRepository.findByIdIn(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        return applications
            .stream()
            .map(applicationMapper::toDomain)
            .toList();
    }

    @Override
    public List<ApplicationDetailVO> getDetailsByIds(List<Long> applicationIds) {
        return queryFactory
            .select(
                new QQueryApplicationDetailVO(
                    applicationEntity.id,
                    studentEntity.id,
                    companyEntity.id,
                    companyEntity.businessArea,
                    applicationEntity.applicationStatus
                )
            )
            .from(applicationEntity)
            .join(applicationEntity.student, studentEntity)
            .join(applicationEntity.recruitment, recruitmentEntity)
            .join(recruitmentEntity.company, companyEntity)
            .where(applicationEntity.id.in(applicationIds))
            .fetch().stream()
            .map(ApplicationDetailVO.class::cast)
            .toList();
    }

    @Override
    public Application getByIdOrThrow(Long applicationId) {
        return applicationJpaRepository.findByIdFetch(applicationId)
            .map(applicationMapper::toDomain)
            .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }

    @Override
    public Application getByIdAndApplicationStatusOrThrow(Long applicationId, ApplicationStatus applicationStatus) {
        return Optional.ofNullable(queryFactory
                .selectFrom(applicationEntity)
                .where(
                    applicationEntity.id.eq(applicationId),
                    applicationEntity.applicationStatus.eq(applicationStatus)
                )
                .fetchOne())
            .map(applicationMapper::toDomain)
            .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }

    @Override
    public Application getByCompanyIdAndStudentIdOrThrow(Long companyId, Long studentId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(applicationEntity)
                .join(applicationEntity.recruitment, recruitmentEntity)
                .on(recruitmentEntity.company.id.eq(companyId))
                .where(applicationEntity.student.id.eq(studentId))
                .fetchFirst())
            .map(applicationMapper::toDomain)
            .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(Application application) {
        applicationJpaRepository.delete(
            applicationMapper.toEntity(application)
        );
    }

    @Override
    public void updateApplicationStatus(ApplicationStatus status, List<Long> applicationIds) {
        queryFactory
            .update(applicationEntity)
            .set(applicationEntity.applicationStatus, status)
            .set(applicationEntity.updatedAt, LocalDateTime.now())
            .where(applicationEntity.id.in(applicationIds))
            .execute();
    }

    @Override
    public void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Long> applicationIds) {
        queryFactory
            .update(applicationEntity)
            .set(applicationEntity.startDate, startDate)
            .set(applicationEntity.endDate, endDate)
            .where(applicationEntity.id.in(applicationIds))
            .execute();
    }

    @Override
    public boolean existsByStudentIdAndApplicationStatusIn(
        Long studentId,
        List<ApplicationStatus> applicationStatuses
    ) {
        return queryFactory
            .selectOne()
            .from(applicationEntity)
            .where(
                applicationEntity.student.id.eq(studentId),
                applicationEntity.applicationStatus.in(applicationStatuses),
                eqYear(Year.now())
            ).fetchFirst() != null;
    }

    @Override
    public boolean existsByStudentIdAndRecruitmentId(Long studentId, Long recruitmentId) {
        return queryFactory
            .selectOne()
            .from(applicationEntity)
            .where(
                applicationEntity.student.id.eq(studentId),
                applicationEntity.recruitment.id.eq(recruitmentId)
            ).fetchFirst() != null;
    }

    @Override
    public boolean existsByStudentIdsAndApplicationStatusInAndRecuritmentId(List<Long> studentIds, List<ApplicationStatus> applicationStatuses, Long recruitmentId) {
        return queryFactory
            .selectOne()
            .from(applicationEntity)
            .where(
                applicationEntity.student.id.in(studentIds),
                applicationEntity.recruitment.id.eq(recruitmentId),
                applicationEntity.applicationStatus.in(applicationStatuses)
            ).fetchFirst() != null;
    }

    @Override
    public void saveAll(List<Application> applications) {
        applicationJpaRepository.saveAll(
            applications.stream()
                .map(applicationMapper::toEntity)
                .toList()
        );
    }

    @Override
    public void deleteAllByApplicationId(Long applicationId) {
        applicationJpaRepository.deleteAttachmentsByApplicationId(applicationId);
    }

    @Override
    public List<ApplicationStatus> getApplicationStatusByStudentId(Long studentId) {
        return queryFactory
            .select(applicationEntity.applicationStatus)
            .from(applicationEntity)
            .where(applicationEntity.student.id.eq(studentId))
            .fetch();
    }

    //==conditions==//

    private BooleanExpression eqRecruitmentId(Long recruitmentId) {
        return recruitmentId == null ? null : recruitmentEntity.id.eq(recruitmentId);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? null : studentEntity.id.eq(studentId);
    }

    private BooleanExpression eqApplicationStatus(ApplicationStatus status) {
        return status == null ? null : applicationEntity.applicationStatus.eq(status);
    }

    private BooleanExpression containStudentName(String studentName) {
        return studentName == null ? null : studentEntity.name.contains(studentName);
    }

    private BooleanExpression eqWinterIntern(Boolean winterIntern) {
        return winterIntern == null ? null : applicationEntity.recruitment.winterIntern.eq(winterIntern);
    }

    private BooleanExpression recentRecruitment(Long companyId) {
        return recruitmentEntity.createdAt.eq(
            select(recruitmentEntity.createdAt.max())
                .from(recruitmentEntity)
                .where(recruitmentEntity.company.id.eq(companyId))
        );
    }

    private BooleanExpression eqYear(Year year) {
        return year == null ? null : applicationEntity.createdAt.year().eq(year.getValue());
    }

    @Override
    public boolean existsAllByApplicationIdsAndCompanyId(List<Long> applicationIds, Long companyId) {
        if (applicationIds == null || applicationIds.isEmpty()) {
            return false;
        }

        long count = queryFactory
            .selectFrom(applicationEntity)
            .join(recruitmentEntity).on(applicationEntity.recruitment.id.eq(recruitmentEntity.id))
            .where(
                applicationEntity.id.in(applicationIds),
                recruitmentEntity.company.id.eq(companyId)
            )
            .stream().count();

        Set<Long> uniqueIds = new HashSet<>(applicationIds);
        return count == uniqueIds.size();
    }
}
