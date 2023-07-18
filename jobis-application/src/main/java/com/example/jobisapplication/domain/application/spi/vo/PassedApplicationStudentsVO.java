package com.example.jobisapplication.domain.application.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PassedApplicationStudentsVO {

    private final Long applicationId;
    private final String studentName;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;
}
