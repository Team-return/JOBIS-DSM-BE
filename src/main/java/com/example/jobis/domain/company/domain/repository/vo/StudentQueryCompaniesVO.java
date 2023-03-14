package com.example.jobis.domain.company.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;

public class StudentQueryCompaniesVO {
    private final String name;
    private final String logoUrl;
    private final Integer take;

    @QueryProjection
    public StudentQueryCompaniesVO(String name, String logoUrl, Integer take) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.take = take;
    }
}
