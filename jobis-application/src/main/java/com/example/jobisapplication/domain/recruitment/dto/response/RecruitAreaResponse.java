package com.example.jobisapplication.domain.recruitment.dto.response;

import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitAreaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitAreaResponse {

    private final Long id;

    private final String job;

    private final List<String> tech;

    private final int hiring;

    private final String majorTask;
}
