package com.example.jobis.domain.company.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExistsCompanyResponse {

    private String companyName;
    private boolean exists;
}
