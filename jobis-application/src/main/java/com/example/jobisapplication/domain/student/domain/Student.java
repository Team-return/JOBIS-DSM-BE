package com.example.jobisapplication.domain.student.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Student {

    private final Long id;

    private final String name;

    private final Integer grade;

    private final Integer classRoom;

    private final Integer number;

    private final Gender gender;

    private final Department department;

    private final String profileImageUrl;

}
