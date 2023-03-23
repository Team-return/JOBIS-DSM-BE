package team.returm.jobis.domain.recruitment.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.code.domain.QRecruitAreaCode;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;
import team.returm.jobis.domain.company.domain.QCompany;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QQueryRecruitmentsVO;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QueryRecruitmentsVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.returm.jobis.domain.code.domain.QRecruitAreaCode;

import static com.querydsl.core.group.GroupBy.set;
import static team.returm.jobis.domain.recruitment.domain.QRecruitArea.recruitArea;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.sum;
import static team.returm.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static team.returm.jobis.domain.company.domain.QCompany.company;
import static team.returm.jobis.domain.recruitment.domain.QRecruitArea.recruitArea;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {
    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<QueryRecruitmentsVO> queryRecruitmentsByConditions(Integer year, LocalDate start, LocalDate end,
                                                                   RecruitStatus status, String companyName, Integer page, List<String> keywords) {
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
                        inKeywords(keywords),
                        recruitment.eq(recruitment),
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
                                        sum(recruitArea.hiredCount),
                                        recruitment.applicationCount
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
        if (start == null || end == null) return null;

        return recruitment.recruitDate.startDate.after(start)
                .and(recruitment.recruitDate.finishDate.before(end));
    }

    private BooleanExpression eqRecruitStatus(RecruitStatus status) {
        return status == null ? null : recruitment.status.eq(status);
    }

    private BooleanExpression containName(String name) {
        if (name == null) return null;

        return company.name.contains(name);
    }

    private BooleanExpression inKeywords(List<String> keywords) {
        return keywords == null ? null : recruitment.recruitAreaList.any().codeList.any().codeKeyword.in(keywords);
    }
}
