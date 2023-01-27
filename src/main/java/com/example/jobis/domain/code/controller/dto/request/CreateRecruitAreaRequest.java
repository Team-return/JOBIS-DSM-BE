package com.example.jobis.domain.code.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateRecruitAreaRequest {

    @NotNull(message = "job은 null일 수 없습니다.")
    private List<Long> job;
    @NotNull(message = "tech는 null일 수 없습니다.")
    private List<Long> tech;
    @NotNull(message = "hiring은 null일 수 없습니다.")
    private int hiring;
    @NotBlank(message = "major_task는 null 또는 공백일 수 없습니다.")
    private String majorTask;
}
