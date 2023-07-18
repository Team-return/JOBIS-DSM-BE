package com.example.jobisapplication.domain.application.dto.request;

import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ChangeApplicationsStatusRequest {

    private List<Long> applicationIds;

    private ApplicationStatus status;
}
