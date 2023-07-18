package team.retum.jobis.domain.recruitment.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.persistence.QApplication;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;
import team.retum.jobis.domain.code.persistence.CodeEntity;
import team.retum.jobis.domain.code.persistence.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.repository.RecruitAreaCodeJpaRepository;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.recruitment.persistence.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QRecruitAreaVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QueryRecruitmentsVO;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.RecruitAreaVO;
import team.retum.jobis.domain.recruitment.presentation.dto.RecruitmentFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.retum.jobis.domain.bookmark.persistence.QBookmark.bookmark;
import static team.retum.jobis.domain.code.persistence.QCode.code;
import static team.retum.jobis.domain.code.persistence.QRecruitAreaCode.recruitAreaCode;
import static team.retum.jobis.domain.company.persistence.QCompany.company;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitArea.recruitArea;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitment.recruitment;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {

    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<QueryRecruitmentsVO> queryRecruitmentsByConditions(RecruitmentFilter filter) {
        QApplication requestedApplication = new QApplication("requestedApplication");
        QApplication approvedApplication = new QApplication("approvedApplication");

        return queryFactory
                .select(
                        new QQueryRecruitmentsVO(
                                recruitment.id,
                                recruitment.status,
                                recruitment.recruitDate,
                                company.name,
                                company.type,
                                recruitment.payInfo.trainPay,
                                recruitment.militarySupport,
                                company.companyLogoUrl,
                                Expressions.stringTemplate("group_concat({0})", recruitArea.jobCodes),
                                recruitArea.hiredCount.sum(),
                                requestedApplication.countDistinct(),
                                approvedApplication.countDistinct(),
                                bookmark.count()
                        )
                )
                .from(recruitment)
                .join(recruitment.recruitAreas, recruitArea)
                .join(recruitment.company, company)
                .leftJoin(recruitment.applications, requestedApplication)
                .on(requestedApplication.applicationStatus.eq(ApplicationStatus.REQUESTED))
                .leftJoin(recruitment.applications, approvedApplication)
                .on(approvedApplication.applicationStatus.eq(ApplicationStatus.APPROVED))
                .leftJoin(bookmark)
                .on(eqStudentId(filter.getStudentId()))
                .where(
                        eqYear(filter.getYear()),
                        betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                        eqRecruitStatus(filter.getStatus()),
                        containsName(filter.getCompanyName()),
                        containsCodes(filter.getCodeEntities()),
                        containsJobKeyword(filter.getJobKeyword())
                )
                .offset(filter.getOffset())
                .limit(11)
                .orderBy(recruitment.createdAt.desc())
                .groupBy(recruitment.id)
                .fetch();
    }

    public Long getRecruitmentCountByCondition(RecruitmentFilter filter) {
        return queryFactory
                .select(recruitment.count())
                .from(recruitment)
                .join(recruitment.company, company)
                .where(
                        eqYear(filter.getYear()),
                        betweenRecruitDate(filter.getStartDate(), filter.getEndDate()),
                        eqRecruitStatus(filter.getStatus()),
                        containsName(filter.getCompanyName())
                ).fetchOne();
    }

    public List<RecruitAreaVO> queryRecruitAreasByRecruitmentId(Long recruitmentId) {
        return queryFactory
                .selectFrom(recruitArea)
                .join(recruitArea.recruitAreaCodes, recruitAreaCode)
                .join(recruitAreaCode.code, code)
                .where(recruitArea.recruitment.id.eq(recruitmentId))
                .transform(
                        groupBy(recruitArea.id)
                                .list(
                                        new QRecruitAreaVO(
                                                recruitArea.id,
                                                recruitArea.hiredCount,
                                                recruitArea.majorTask,
                                                recruitArea.jobCodes,
                                                list(code)
                                        )
                                )
                );
    }

    public Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(recruitment)
                .where(recruitment.company.id.eq(companyId))
                .orderBy(recruitment.createdAt.desc())
                .fetchFirst());
    }

    public List<Recruitment> queryApprovedRecruitmentsAfterRecruitDate() {
        return queryFactory
                .selectFrom(recruitment)
                .where(
                        recruitment.recruitDate.startDate.before(LocalDate.now()),
                        recruitment.status.ne(RecruitStatus.REQUESTED)
                )
                .fetch();
    }

    public Optional<RecruitArea> queryRecruitAreaById(Long recruitAreaId) {
        return recruitAreaJpaRepository.findById(recruitAreaId);
    }

    public void deleteRecruitAreaCodeByRecruitAreaId(Long recruitAreaId) {
        recruitAreaCodeJpaRepository.deleteAllByRecruitAreaId(recruitAreaId);
    }

    public void deleteRecruitAreaById(Long recruitAreaId) {
        recruitAreaJpaRepository.deleteById(recruitAreaId);
    }

    public void saveAllRecruitments(List<Recruitment> recruitments) {
        recruitmentJpaRepository.saveAll(recruitments);
    }

    public Optional<Recruitment> queryRecruitmentById(Long recruitmentId) {
        return recruitmentJpaRepository.findById(recruitmentId);
    }

    public void saveAllRecruitAreaCodes(List<RecruitAreaCodeEntity> recruitAreaCodeEntities) {
        recruitAreaCodeJpaRepository.saveAll(recruitAreaCodeEntities);
    }

    public void deleteRecruitment(Long recruitmentId) {
        recruitmentJpaRepository.deleteById(recruitmentId);
    }

    public Recruitment saveRecruitment(Recruitment recruitment) {
        return recruitmentJpaRepository.save(recruitment);
    }

    public RecruitArea saveRecruitArea(RecruitArea recruitArea) {
        return recruitAreaJpaRepository.save(recruitArea);
    }

    public List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds) {
        return recruitmentJpaRepository.findByIdIn(recruitmentIds);
    }

    public boolean existsRecruitmentByCompanyAndStatusNot(Company company, RecruitStatus status) {
        return recruitmentJpaRepository.existsByCompanyAndStatusNot(company, status);
    }

    //===conditions===//

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitment.recruitYear.eq(year);
    }

    private BooleanExpression betweenRecruitDate(LocalDate start, LocalDate end) {
        if (start == null && end == null) return null;

        if (start == null) {
            return recruitment.recruitDate.finishDate.before(end.plusDays(1));
        }

        if (end == null) {
            return recruitment.recruitDate.startDate.after(start.minusDays(1));
        }

        return recruitment.recruitDate.startDate.after(start.minusDays(1))
                .and(recruitment.recruitDate.finishDate.before(end.plusDays(1)));
    }

    private BooleanExpression eqRecruitStatus(RecruitStatus status) {
        return status == null ? null : recruitment.status.eq(status);
    }

    private BooleanExpression containsName(String name) {
        if (name == null) return null;

        return company.name.contains(name);
    }

    private BooleanExpression containsCodes(List<CodeEntity> codeEntities) {
        return codeEntities == null ? null : recruitArea.recruitAreaCodes.any().code.in(codeEntities);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? bookmark.recruitment.eq(recruitment) : bookmark.student.id.eq(studentId).and(bookmark.recruitment.eq(recruitment));
    }

    private BooleanExpression containsJobKeyword(String jobKeyword) {
        return jobKeyword == null ? null : recruitArea.jobCodes.contains(jobKeyword);
    }
}