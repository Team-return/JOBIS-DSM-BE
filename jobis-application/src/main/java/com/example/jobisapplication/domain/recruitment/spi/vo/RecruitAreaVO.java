package com.example.jobisapplication.domain.recruitment.spi.vo;

import com.example.jobisapplication.domain.code.model.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitAreaVO {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String jobCodes;

    private final List<Code> techCodes;
}
