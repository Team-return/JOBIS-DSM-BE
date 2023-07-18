package com.example.jobisapplication.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.persistence.repository.vo.StudentQueryCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentQueryCompaniesResponse {
    private final List<StudentQueryCompaniesVO> companies;
}
