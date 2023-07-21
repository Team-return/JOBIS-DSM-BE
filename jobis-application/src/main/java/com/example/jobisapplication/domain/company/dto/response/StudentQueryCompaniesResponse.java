package com.example.jobisapplication.domain.company.dto.response;

import com.example.jobisapplication.domain.company.spi.vo.StudentCompaniesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentCompaniesVO> companies;
}
