package com.example.jobis.domain.recruit.domain.repository.vo;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class QueryRecruitmentListVO {
    private final Long id;
    private final RecruitStatus status;
    private final String companyName;
    private final CompanyType companyType;
    private final LocalDate start;
    private final LocalDate end;
    private final boolean militarySupport;
    private final Set<String> recruitAreaList;
    private final Integer totalHiring;

    @QueryProjection
    public QueryRecruitmentListVO(Long id, RecruitStatus status, String companyName, CompanyType companyType,
                                  LocalDate start, LocalDate end, boolean militarySupport, Integer totalHiring,
                                  Set<String> recruitAreaList) {
        this.id = id;
        this.status = status;
        this.companyName = companyName;
        this.companyType = companyType;
        this.start = start;
        this.end = end;
        this.militarySupport = militarySupport;
        this.recruitAreaList = recruitAreaList;
        this.totalHiring = totalHiring;
    }
}
