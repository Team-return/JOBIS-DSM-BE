package com.example.jobisapplication.domain.recruitment.dto.request;

import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class RecruitAreaRequest {

    private List<Long> jobCodes;

    private List<Long> techCodes;

    private int hiring;

    private String majorTask;

    public List<Long> getCodes() {
        return Stream.of(jobCodes, techCodes)
                .flatMap(Collection::stream)
                .toList();
    }
}
