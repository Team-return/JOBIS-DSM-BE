package com.example.jobis.domain.recruitment.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateRecruitAreaRequest {

    @NotNull(message = "hiring은 null일 수 없습니다.")
    private int hiring;

    @NotBlank(message = "major_task는 null 또는 공백일 수 없습니다.")
    private String majorTask;
}
