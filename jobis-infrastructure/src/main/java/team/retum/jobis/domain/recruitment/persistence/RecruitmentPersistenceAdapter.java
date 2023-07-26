package team.retum.jobis.domain.recruitment.persistence;

import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.code.model.RecruitAreaCode;
import com.example.jobisapplication.domain.recruitment.dto.RecruitmentFilter;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.RecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentDetailVO;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.persistence.entity.QApplicationEntity;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.mapper.CodeMapper;
import team.retum.jobis.domain.code.persistence.mapper.RecruitAreaCodeMapper;
import team.retum.jobis.domain.code.persistence.repository.RecruitAreaCodeJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitAreaMapper;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitmentMapper;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitAreaVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitmentsVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.retum.jobis.domain.bookmark.persistence.entity.QBookmarkEntity.bookmarkEntity;
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
    private final CodeMapper codeMapper;
    private final RecruitmentMapper recruitmentMapper;
    private final RecruitAreaMapper recruitAreaMapper;
    private final RecruitAreaCodeMapper recruitAreaCodeMapper;

    @Override
    public List<RecruitmentVO> queryRecruitmentsByFilter(RecruitmentFilter filter) {
        QApplicationEntity requestedApplication = new QApplicationEntity("requestedApplication");
        QApplicationEntity approvedApplication = new QApplicationEntity("approvedApplication");

        return queryFactory
                .select(
                        new QQueryRecruitmentsVO(
                                recruitmentEntity.id,
                                recruitmentEntity.status,
                                recruitmentEntity.recruitDate.startDate,
                                recruitmentEntity.recruitDate.finishDate,
                                companyEntity.name,
                                companyEntity.type,
                                recruitmentEntity.payInfo.trainPay,
                                recruitmentEntity.militarySupport,
                                companyEntity.companyLogoUrl,
                                Expressions.stringTemplate("group_concat({0})", recruitAreaEntity.jobCodes),
                                recruitAreaEntity.hiredCount.sum(),
                                requestedApplication.countDistinct(),
                                approvedApplication.countDistinct(),
                                bookmarkEntity.count()
                        )
                )
                .from(recruitmentEntity)
                .join(recruitAreaEntity)
                .on(recruitAreaEntity.recruitmentEntity.eq(recruitmentEntity))
                .join(recruitAreaCodeEntity)
                .on(recruitAreaCodeEntity.recruitAreaEntity.in(recruitAreaEntity))
                .join(recruitmentEntity.companyEntity)
                .leftJoin(requestedApplication)
                .on(
                        requestedApplication.applicationStatus.eq(ApplicationStatus.REQUESTED),
                        requestedApplication.recruitmentEntity.eq(recruitmentEntity)
                )
                .leftJoin(approvedApplication)
                .on(
                        approvedApplication.applicationStatus.eq(ApplicationStatus.APPROVED),
                        approvedApplication.recruitmentEntity.eq(recruitmentEntity)
                )
                .leftJoin(bookmarkEntity)
                .on(eqStudentId(filter.getStudentId()))
                .where(
                        eqYear(filter.getYear()),
                        betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                        eqRecruitStatus(filter.getStatus()),
                        containsName(filter.getCompanyName()),
                        containsCodes(filter.getCodeEntities().stream()
                                .map(codeMapper::toEntity).toList()),
                        containsJobKeyword(filter.getJobKeyword())
                )
                .offset(filter.getOffset())
                .limit(11)
                .orderBy(recruitmentEntity.createdAt.desc())
                .groupBy(recruitmentEntity.id)
                .fetch().stream()
                .map(recruitment -> (RecruitmentVO) recruitment)
                .toList();
    }

    @Override
    public RecruitmentDetailVO queryRecruitmentDetailById(Long recruitmentId) {
        return queryFactory
                .select(
                        new QQueryRecruitmentDetailVO(
                                companyEntity.id,
                                companyEntity.companyLogoUrl,
                                companyEntity.name,
                                recruitmentEntity.preferentialTreatment,
                                recruitmentEntity.requiredGrade,
                                recruitmentEntity.workingHours,
                                recruitmentEntity.requiredLicenses,
                                recruitmentEntity.hiringProgress,
                                recruitmentEntity.payInfo.trainPay,
                                recruitmentEntity.payInfo.pay,
                                recruitmentEntity.benefits,
                                recruitmentEntity.militarySupport,
                                recruitmentEntity.submitDocument,
                                recruitmentEntity.recruitDate.startDate,
                                recruitmentEntity.recruitDate.finishDate,
                                recruitmentEntity.etc
                        )
                )
                .from(recruitmentEntity)
                .join(recruitmentEntity.companyEntity, companyEntity)
                .where(recruitmentEntity.id.eq(recruitmentId))
                .fetchOne();
    }

    @Override
    public Long getRecruitmentCountByFilter(RecruitmentFilter filter) {
        return queryFactory
                .select(recruitmentEntity.count())
                .from(recruitmentEntity)
                .join(recruitmentEntity.companyEntity, companyEntity)
                .where(
                        eqYear(filter.getYear()),
                        betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                        eqRecruitStatus(filter.getStatus()),
                        containsName(filter.getCompanyName())
                ).fetchOne();
    }

    @Override
    public List<RecruitAreaResponse> queryRecruitAreasByRecruitmentId(Long recruitmentId) {
        return queryFactory
                .selectFrom(recruitAreaEntity)
                .join(recruitAreaCodeEntity)
                .on(recruitAreaCodeEntity.recruitAreaEntity.in(recruitAreaEntity))
                .join(recruitAreaCodeEntity.codeEntity, codeEntity)
                .where(recruitAreaEntity.recruitmentEntity.id.eq(recruitmentId))
                .transform(
                        groupBy(recruitAreaEntity.id)
                                .list(
                                        new QQueryRecruitAreaVO(
                                                recruitAreaEntity.id,
                                                recruitAreaEntity.hiredCount,
                                                recruitAreaEntity.majorTask,
                                                recruitAreaEntity.jobCodes,
                                                list(codeEntity)
                                        )
                                )
                ).stream()
                .map(recruitArea -> (RecruitAreaResponse) recruitArea)
                .toList();
    }

    @Override
    public Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId) {
        return Optional.ofNullable(
                        queryFactory
                                .selectFrom(recruitmentEntity)
                                .where(recruitmentEntity.companyEntity.id.eq(companyId))
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
                .where(recruitAreaEntity.recruitmentEntity.id.eq(recruitmentId))
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
    public void saveAllRecruitmentAreaCodes(List<RecruitAreaCode> recruitAreaCodes) {
        recruitAreaCodeJpaRepository.saveAll(
                recruitAreaCodes.stream()
                        .map(recruitAreaCodeMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public void deleteRecruitment(Recruitment recruitment) {
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
        return recruitAreaMapper.toDomain(
                recruitAreaJpaRepository.save(recruitAreaMapper.toEntity(recruitArea))
        );
    }

    public List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds) {
        return recruitmentJpaRepository.findByIdIn(recruitmentIds).stream()
                .map(recruitmentMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsOnRecruitmentByCompanyId(Long companyId) {
        return recruitmentJpaRepository.existsByCompanyEntityIdAndStatusNot(companyId, RecruitStatus.DONE);
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

    private BooleanExpression containsCodes(List<CodeEntity> codeEntities) {
        return codeEntities == null ? null : recruitAreaCodeEntity.codeEntity.in(codeEntities);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? bookmarkEntity.recruitmentEntity.eq(recruitmentEntity) : bookmarkEntity.studentEntity.id.eq(studentId).and(bookmarkEntity.recruitmentEntity.eq(recruitmentEntity));
    }

    private BooleanExpression containsJobKeyword(String jobKeyword) {
        return jobKeyword == null ? null : recruitAreaEntity.jobCodes.contains(jobKeyword);
    }
}