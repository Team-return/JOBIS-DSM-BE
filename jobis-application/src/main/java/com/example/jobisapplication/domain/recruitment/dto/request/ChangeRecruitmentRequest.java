package com.example.jobisapplication.domain.recruitment.dto.request;

import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ChangeRecruitmentRequest {

    private List<Long> recruitmentIds;

    private RecruitStatus status;
}
