package team.returm.jobis.domain.recruitment.domain.repository;

import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QQueryRecruitmentsVO;
import team.returm.jobis.domain.recruitment.domain.repository.vo.QueryRecruitmentsVO;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.returm.jobis.domain.code.domain.QRecruitAreaCode;
import team.returm.jobis.domain.company.domain.QCompany;


import static team.returm.jobis.domain.recruitment.domain.QRecruitArea.recruitArea;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static com.querydsl.core.group.GroupBy.groupBy;
import static team.returm.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static team.returm.jobis.domain.company.domain.QCompany.company;
import static com.querydsl.core.group.GroupBy.sum;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {
    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<QueryRecruitmentsVO> queryRecruitmentsByConditions(Integer year, LocalDate start, LocalDate end,
                                                                   RecruitStatus status, String companyName, Integer page) {
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
                        recruitment.eq(recruitment),
                        QRecruitAreaCode.recruitAreaCode.codeType.eq(CodeType.JOB)
                )
                .orderBy(recruitment.createdAt.desc())
                .offset(page * pageSize)
                .limit(pageSize)
                .transform(
                        groupBy(recruitArea.recruitment.id)
                                .list(new QQueryRecruitmentsVO(
                                        recruitment,
                                        QCompany.company,
                                        GroupBy.set(recruitAreaCode.codeKeyword),
                                        sum(recruitArea.hiredCount),
                                        recruitment.applicationCount
                                ))
                );
    }

    public List<RecruitArea> queryRecruitAreasByRecruitmentId(UUID recruitmentId) {
        return queryFactory
                .selectFrom(recruitArea).distinct()
                .join(recruitArea.codeList, QRecruitAreaCode.recruitAreaCode).fetchJoin()
                .where(recruitArea.recruitment.id.eq(recruitmentId))
                .fetch();
    }

    public List<Recruitment> queryRecruitmentsAfterRecruitDate() {
        return queryFactory
                .selectFrom(recruitment)
                .where(recruitment.recruitDate.startDate.before(LocalDate.now()))
                .fetch();
    }

    public void deleteRecruitAreaCodeByRecruitAreaId(UUID recruitAreaId) {
        recruitAreaCodeJpaRepository.deleteAllByRecruitAreaId(recruitAreaId);
    }

    public void saveAllRecruitments(List<Recruitment> recruitments) {
        recruitmentJpaRepository.saveAll(recruitments);
    }

    public Optional<Recruitment> queryRecruitmentById(UUID recruitmentId) {
        return recruitmentJpaRepository.findById(recruitmentId);
    }

    public void saveAllRecruitAreaCodes(List<RecruitAreaCode> recruitAreaCodes) {
        recruitAreaCodeJpaRepository.saveAll(recruitAreaCodes);
    }

    public Recruitment saveRecruitment(Recruitment recruitment) {
        return recruitmentJpaRepository.save(recruitment);
    }

    public RecruitArea saveRecruitArea(RecruitArea recruitArea) {
        return recruitAreaJpaRepository.save(recruitArea);
    }

    //===conditions===//

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : recruitment.recruitYear.eq(year);
    }

    private BooleanExpression betweenRecruitDate(LocalDate start, LocalDate end) {
        if(start == null || end == null) return null;

        return recruitment.recruitDate.startDate.after(start)
                .and(recruitment.recruitDate.finishDate.before(end));
    }

    private BooleanExpression eqRecruitStatus(RecruitStatus status) {
        return status == null ? null : recruitment.status.eq(status);
    }

    private BooleanExpression containName(String name) {
        if(name == null) return null;

        return QCompany.company.name.contains(name);
    }
}
