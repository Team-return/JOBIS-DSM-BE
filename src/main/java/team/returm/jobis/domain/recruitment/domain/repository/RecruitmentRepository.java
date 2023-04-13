package team.returm.jobis.domain.recruitment.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.code.domain.QRecruitAreaCode;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QQueryRecruitmentsVO;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QueryRecruitmentsVO;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.set;
import static team.returm.jobis.domain.application.domain.QApplication.application;
import static team.returm.jobis.domain.recruitment.domain.QRecruitArea.recruitArea;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.sum;
import static team.returm.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static team.returm.jobis.domain.company.domain.QCompany.company;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {

    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<QueryRecruitmentsVO> queryRecruitmentsByConditions(Integer year, LocalDate start, LocalDate end,
                                                                   RecruitStatus status, String companyName,
                                                                   Integer page, List<RecruitAreaCode> codes) {
        long pageSize = 11;
        return queryFactory.selectFrom(recruitArea)
                .leftJoin(recruitArea.recruitment, recruitment)
                .leftJoin(recruitment.company, company)
                .leftJoin(recruitArea.codeList, recruitAreaCode)
                .where(
                        eqYear(year),
                        betweenRecruitDate(start, end),
                        eqRecruitStatus(status),
                        containName(companyName),
                        containsKeywords(codes),
                        recruitAreaCode.codeType.eq(CodeType.JOB)
                )
                .orderBy(recruitment.createdAt.desc())
                .offset(page * pageSize)
                .limit(pageSize)
                .transform(
                        groupBy(recruitArea.recruitment.id)
                                .list(new QQueryRecruitmentsVO(
                                        recruitment,
                                        company,
                                        set(recruitAreaCode.codeKeyword),
                                        sum(recruitArea.hiredCount)
                                ))
                );
    }

    public List<RecruitArea> queryRecruitAreasByRecruitmentId(Long recruitmentId) {
        return queryFactory
                .selectFrom(recruitArea).distinct()
                .join(recruitArea.codeList, QRecruitAreaCode.recruitAreaCode).fetchJoin()
                .where(recruitArea.recruitment.id.eq(recruitmentId))
                .fetch();
    }

    public Recruitment queryRecentRecruitmentByCompanyId(Long companyId) {
        return queryFactory
                .selectFrom(recruitment)
                .where(recruitment.company.id.eq(companyId))
                .orderBy(recruitment.createdAt.desc())
                .fetchFirst();
    }

    public List<Recruitment> queryRecruitmentsAfterRecruitDate() {
        return queryFactory
                .selectFrom(recruitment)
                .where(recruitment.recruitDate.startDate.before(LocalDate.now()))
                .fetch();
    }

    public void queryRecruitmentsByApplications(List<Application> applications) {
        queryFactory
                .select(recruitment.id)
                .from(recruitment)
                .join(recruitment.applications, application)
                .where(application.in(applications))
                .fetch();
    }

    public void addApplicationApprovedCount(List<Long> recruitmentIds) {
        queryFactory
                .update(recruitment)
                .set(recruitment.applicationApprovedCount, recruitment.applicationApprovedCount.add(1))
                .set(recruitment.applicationRequestedCount, recruitment.applicationRequestedCount.subtract(1))
                .where(recruitment.id.in(recruitmentIds))
                .execute();
    }

    public void addApplicationRequestedCount(List<Long> recruitmentIds) {
        queryFactory
                .update(recruitment)
                .set(recruitment.applicationRequestedCount, recruitment.applicationRequestedCount.add(1))
                .set(recruitment.applicationApprovedCount, recruitment.applicationApprovedCount.subtract(1))
                .where(recruitment.id.in(recruitmentIds))
                .execute();
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

    public void saveAllRecruitAreaCodes(List<RecruitAreaCode> recruitAreaCodes) {
        recruitAreaCodeJpaRepository.saveAll(recruitAreaCodes);
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

    private BooleanExpression containName(String name) {
        if (name == null) return null;

        return company.name.contains(name);
    }

    private BooleanExpression containsKeywords(List<RecruitAreaCode> codes) {
        return codes == null ? null : recruitment.recruitAreaList.any().codeList.any().in(codes);
    }
}
