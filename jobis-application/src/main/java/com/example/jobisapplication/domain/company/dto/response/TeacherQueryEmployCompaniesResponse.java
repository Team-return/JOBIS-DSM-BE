package com.example.jobisapplication.domain.company.dto.response;

import com.example.jobisapplication.domain.company.spi.vo.TeacherEmployCompaniesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryEmployCompaniesResponse {

    private final List<TeacherEmployCompaniesVO> companies;
}
