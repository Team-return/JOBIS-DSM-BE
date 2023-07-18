package com.example.jobisapplication.domain.company.dto.response;

import com.example.jobisapplication.domain.company.spi.vo.StudentQueryCompaniesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentQueryCompaniesVO> companies;
}
