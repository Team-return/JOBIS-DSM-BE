package com.example.jobisapplication.domain.code.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class RecruitAreaCode {

    private final Long recruitAreaId;

    private final Long codeId;

}
