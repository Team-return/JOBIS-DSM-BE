package com.example.jobis.domain.recruit.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRecruitAreaRequest {

    private List<Long> job;

    private List<Long> tech;

    private int hiring;

    private String majorTask;
}
