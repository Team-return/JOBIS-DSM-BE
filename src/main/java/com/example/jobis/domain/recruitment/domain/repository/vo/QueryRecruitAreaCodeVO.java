package com.example.jobis.domain.recruitment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class QueryRecruitAreaCodeVO {
    private final Long id;
    private final List<String> keyword;

    @QueryProjection
    public QueryRecruitAreaCodeVO(Long id, List<String> keyword) {
        this.id = id;
        this.keyword = keyword;
    }
}
