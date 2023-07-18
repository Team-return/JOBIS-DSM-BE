package com.example.jobisapplication.domain.company.dto.response;

import com.example.jobisapplication.domain.company.spi.vo.TeacherQueryEmployCompaniesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryEmployCompaniesResponse {

    private final List<TeacherQueryEmployCompaniesVO> companies;
}
