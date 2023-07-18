package com.example.jobisapplication.domain.teacher.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Teacher {

    private final Long id;
    
}
