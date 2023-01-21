package com.example.jobis.domain.recruit.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryRecruitAreaCodeVO {
    private final String keyword;

    @QueryProjection
    public QueryRecruitAreaCodeVO(String keyword) {
        this.keyword = keyword;
    }
}
