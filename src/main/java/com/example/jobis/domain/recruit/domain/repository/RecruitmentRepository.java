package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.vo.QQueryRecruitmentListVO;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitmentListVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


import static com.example.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static com.example.jobis.domain.code.domain.QCode.code1;
import static com.example.jobis.domain.recruit.domain.QRecruitArea.recruitArea;
import static com.example.jobis.domain.recruit.domain.QRecruitment.recruitment;
import static com.example.jobis.domain.company.domain.QCompany.company;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepository {
    private final JPAQueryFactory queryFactory;
    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public List<QueryRecruitmentListVO> queryRecruitmentsByConditions(Integer year, LocalDate start, LocalDate end,
                                              RecruitStatus status, String companyName, Integer page) {
        long pageSize = 11;
        return queryFactory.selectFrom(recruitArea)
                .leftJoin(recruitArea.recruitment, recruitment)
                .where(
                        eqYear(year),
                        betweenRecruitDate(start, end),
                        eqRecruitStatus(status),
                        containName(companyName)
                )
                .leftJoin(recruitment.company, company)
                .offset(page * pageSize)
                .limit(pageSize)
                .orderBy(recruitment.createdAt.desc())
                .transform(
                        groupBy(recruitArea.recruitment.id)
                                .list(new QQueryRecruitmentListVO(
                                        recruitment.id,
                                        recruitment.status,
                                        company.name,
                                        company.type,
                                        recruitment.recruitDate.startDate,
                                        recruitment.recruitDate.finishDate,
                                        recruitment.militarySupport,
                                        list(recruitArea)
                                ))
                );
    }

    public List<RecruitAreaCode> findAllRecruitCodeByRecruitArea(RecruitArea recruitArea) {
        return queryFactory.selectFrom(recruitAreaCode)
                .where(recruitAreaCode.recruitAreaId.eq(recruitArea),
                        code1.codeType.eq(CodeType.JOB))
                .join(recruitAreaCode.codeId, code1)
                .fetchJoin().fetch();
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
