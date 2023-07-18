package com.example.jobisapplication.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesVO {

    private final Long id;
    private final String name;
    private final String logoUrl;
    private final double take;
    private final boolean hasRecruitment;
}
