package com.example.jobisapplication.domain.acceptance.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ChangeContractDateRequest {

    private List<Long> acceptanceIds;

    private LocalDate contractDate;
}
