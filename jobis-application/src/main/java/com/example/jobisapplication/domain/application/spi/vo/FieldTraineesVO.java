package com.example.jobisapplication.domain.application.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FieldTraineesVO {

    private final Long applicationId;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;
    private final String studentName;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
