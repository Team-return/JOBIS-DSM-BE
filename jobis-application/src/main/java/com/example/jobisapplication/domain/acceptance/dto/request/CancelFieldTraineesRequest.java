package com.example.jobisapplication.domain.acceptance.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CancelFieldTraineesRequest {
    private List<Long> applicationIds;
}
