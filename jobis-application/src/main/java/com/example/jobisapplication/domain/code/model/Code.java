package com.example.jobisapplication.domain.code.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Code {

    private final Long id;

    private final CodeType codeType;

    private final JobType jobType;

    private final String keyword;

    private final Long parentCodeId;
}
