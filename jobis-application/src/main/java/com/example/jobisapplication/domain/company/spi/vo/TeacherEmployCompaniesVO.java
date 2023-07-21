package com.example.jobisapplication.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherEmployCompaniesVO {

    private final Long companyId;
    private final String companyName;
    private final Long fieldTraineeCount;
    private final Long contractCount;
}
