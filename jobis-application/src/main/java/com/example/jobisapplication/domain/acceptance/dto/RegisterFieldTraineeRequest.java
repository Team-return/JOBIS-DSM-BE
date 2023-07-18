package com.example.jobisapplication.domain.acceptance.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RegisterFieldTraineeRequest {

    private List<Long> applicationIds;

    private LocalDate startDate;

    private LocalDate endDate;
}
