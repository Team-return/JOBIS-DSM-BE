package com.example.jobisapplication.domain.acceptance.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegisterEmploymentContractRequest {

    private List<String> codeKeywords;

    private List<Long> applicationIds;
}
