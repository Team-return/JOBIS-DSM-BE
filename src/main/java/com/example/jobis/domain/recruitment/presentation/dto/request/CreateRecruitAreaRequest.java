package com.example.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateRecruitAreaRequest {

    @NotNull
    private List<Long> jobCodes;
    @NotNull
    private List<Long> techCodes;
    @NotNull
    private int hiring;
    @NotBlank
    private String majorTask;
}
