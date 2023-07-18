package com.example.jobisapplication.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.persistence.repository.vo.TeacherQueryEmployCompaniesVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryEmployCompaniesResponse {

    private final List<TeacherQueryEmployCompaniesVO> companies;
}
