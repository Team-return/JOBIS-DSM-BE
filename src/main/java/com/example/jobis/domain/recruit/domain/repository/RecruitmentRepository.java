package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.vo.QQueryRecruitAreaCodeVO;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitAreaCodeVO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static com.example.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static com.example.jobis.domain.code.domain.QCode.code1;
import static com.example.jobis.domain.recruit.domain.QRecruitArea.recruitArea;
import static com.example.jobis.domain.recruit.domain.QRecruitment.recruitment;
import static com.example.jobis.domain.company.domain.QCompany.company;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {
    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<Recruitment> queryRecruitmentsByConditions(Integer year, LocalDate start, LocalDate end,
                                              RecruitStatus status, String companyName, Integer page) {
        return queryFactory
                .selectFrom(recruitment)
                .where(
                        eqYear(year),
                        betweenRecruitDate(start, end),
                        eqRecruitStatus(status),
                        containName(companyName)
                )
                .join(recruitment.company, company)
                .leftJoin(recruitment.recruitAreaList, recruitArea)
                .fetchJoin()
                .offset(page * 4)
                .limit(4)
                .orderBy(recruitment.createdAt.desc())
                .fetch();
    }

    public List<QueryRecruitAreaCodeVO> queryKeywordListByRecruitArea(RecruitArea recruitArea) {
        return queryFactory
                .select(
                        new QQueryRecruitAreaCodeVO(
                                code1.keyword
                        )
                ).from(recruitAreaCode)
                .where(recruitAreaCode.recruitAreaId.eq(recruitArea),
                        code1.codeType.eq(CodeType.JOB))
                .join(recruitAreaCode.codeId, code1)
                .fetch();
    }

    public List<RecruitAreaCode> findAllRecruitCodeByRecruitArea(RecruitArea recruitArea) {
        return queryFactory.selectFrom(recruitAreaCode)
                .where(recruitAreaCode.recruitAreaId.eq(recruitArea),
                        code1.codeType.eq(CodeType.JOB))
                .join(recruitAreaCode.codeId, code1)
                .fetchJoin().fetch();
    }

    public List<Recruitment> findAll() {
        return queryFactory.selectFrom(recruitment)
                .where(recruitment.recruitYear.eq(Year.now().getValue()))
                .join(recruitment.company, company)
                .fetchJoin()
                .join(recruitment.recruitAreaList, recruitArea)
                .fetchJoin()
                .fetch();
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

        return company.name.contains(name);
    }
}
