package com.example.jobis.domain.student.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyListResponse {

    private final List<CompanyResponse> companyList;
}
