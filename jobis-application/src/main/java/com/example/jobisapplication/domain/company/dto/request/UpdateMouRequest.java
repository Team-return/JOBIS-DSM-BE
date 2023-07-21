package com.example.jobisapplication.domain.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateMouRequest {

    private List<Long> companyIds;
}
