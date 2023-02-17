package com.example.jobis.domain.recruit.domain.repository.vo;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.Set;

@Getter
public class QueryRecruitmentListVO {
    private final Recruitment recruitment;
    private final Company company;
    private final Set<String> recruitAreaList;
    private final Integer totalHiring;

    @QueryProjection
    public QueryRecruitmentListVO(Recruitment recruitment, Company company, Set<String> recruitAreaList, Integer totalHiring) {
        this.recruitment = recruitment;
        this.company = company;
        this.recruitAreaList = recruitAreaList;
        this.totalHiring = totalHiring;
    }
}
