package com.example.jobisapplication.domain.company.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateMouRequest {

    private List<Long> companyIds;
}
