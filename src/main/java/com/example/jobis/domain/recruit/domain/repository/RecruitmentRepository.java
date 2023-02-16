package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.vo.QQueryRecruitmentListVO;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitmentListVO;
import com.example.jobis.domain.recruit.exception.RecruitmentNotFoundException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import static com.example.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static com.example.jobis.domain.code.domain.QCode.code;
import static com.example.jobis.domain.recruit.domain.QRecruitArea.recruitArea;
import static com.example.jobis.domain.recruit.domain.QRecruitment.recruitment;
import static com.example.jobis.domain.company.domain.QCompany.company;
import static com.querydsl.core.group.GroupBy.*;

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
                .leftJoin(recruitment.company, company)
                .leftJoin(recruitArea.codeList, recruitAreaCode)
                .leftJoin(recruitAreaCode.codeId, code)
                .where(
                        eqYear(year),
                        betweenRecruitDate(start, end),
                        eqRecruitStatus(status),
                        containName(companyName),
                        recruitment.eq(recruitment),
                        code.codeType.eq(CodeType.JOB)
                )
                .orderBy(recruitment.createdAt.desc())
                .offset(page * pageSize)
                .limit(pageSize)
                .transform(
                        groupBy(recruitArea.recruitment.id)
                                .list(new QQueryRecruitmentListVO(
                                        recruitment,
                                        company,
                                        set(code.keyword),
                                        sum(recruitArea.hiredCount)
                                ))
                );
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

    public Recruitment findRecruitmentById(UUID id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
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
