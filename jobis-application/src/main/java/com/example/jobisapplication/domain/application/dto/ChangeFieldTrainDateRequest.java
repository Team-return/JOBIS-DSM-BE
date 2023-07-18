package com.example.jobisapplication.domain.application.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ChangeFieldTrainDateRequest {

    private List<Long> applicationIds;

    private LocalDate startDate;

    private LocalDate endDate;
}
