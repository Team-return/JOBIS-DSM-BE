package com.example.jobisapplication.domain.student.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class VerifiedStudent {

    private final String gcn;

    private final String name;

}
