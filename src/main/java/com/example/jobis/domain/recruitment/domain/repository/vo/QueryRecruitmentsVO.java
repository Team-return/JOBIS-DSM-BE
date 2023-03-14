package com.example.jobis.domain.recruitment.domain.repository.vo;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.Set;

@Getter
public class QueryRecruitmentsVO {
    private final Recruitment recruitment;
    private final Company company;
    private final Set<String> recruitAreaList;
    private final Integer totalHiring;

    @QueryProjection
    public QueryRecruitmentsVO(Recruitment recruitment, Company company, Set<String> recruitAreaList, Integer totalHiring) {
        this.recruitment = recruitment;
        this.company = company;
        this.recruitAreaList = recruitAreaList;
        this.totalHiring = totalHiring;
    }
}
