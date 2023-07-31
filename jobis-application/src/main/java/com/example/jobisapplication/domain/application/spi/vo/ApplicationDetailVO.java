package com.example.jobisapplication.domain.application.spi.vo;

import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.company.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationDetailVO {

    private final Long id;

    private final String studentName;

    private final int studentGrade;

    private final int studentClassNumber;

    private final int studentNumber;

    private final Long companyId;

    private final String businessArea;

    private final ApplicationStatus status;
}
