package com.example.jobis.domain.recruit.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitListResponse {

    private final List<RecruitResponse> recruitList;
}
