package com.example.jobisapplication.domain.recruitment.dto.request;

import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChangeRecruitmentStatusRequest {

    private List<Long> recruitmentIds;

    private RecruitStatus status;
}
