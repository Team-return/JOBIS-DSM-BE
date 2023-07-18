package com.example.jobisapplication.domain.acceptance.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RegisterEmploymentContractRequest {

    private List<String> codeKeywords;

    private List<Long> applicationIds;
}
